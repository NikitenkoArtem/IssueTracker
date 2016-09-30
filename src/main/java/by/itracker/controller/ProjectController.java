package by.itracker.controller;

import by.itracker.Auth;
import by.itracker.DBConnection;
import by.itracker.dao.BuildDao;
import by.itracker.dao.ManagerDao;
import by.itracker.dao.ProjectDao;
import by.itracker.dao.UserDao;
import by.itracker.entity.Build;
import by.itracker.entity.Manager;
import by.itracker.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "ProjectController", urlPatterns = "/project")
public class ProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie userRole = new Auth(request).getCookieByName("userRole");
        if (userRole != null) {
            if (!userRole.getValue().equals("ADMINISTRATOR")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                final HttpSession session = request.getSession();
                final String action = request.getParameter("action");
                try {
                    try (Connection conn = DBConnection.getConnection()) {
                        switch (action) {
                            case "add": {
                                Project project = getProject(request);
                                new ProjectDao(conn).create(project);
                                session.setAttribute("servlet", "project");
                                response.sendRedirect("/200.jsp");
                                break;
                            }
                            case "edit": {
                                Project project = getProject(request);
                                new ProjectDao(conn).update(project);
                                session.setAttribute("servlet", "project");
                                response.sendRedirect("/200.jsp");
                                break;
                            }
                        }
                    } catch (SQLException e) {
                        throw new Exception(e);
                    }
                } catch (Exception e) {
                    Logger logger = Logger.getLogger(e.getClass().getName());
                    logger.severe(e.getMessage());
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie userRole = new Auth(request).getCookieByName("userRole");
        if (userRole != null) {
            if (!userRole.getValue().equals("ADMINISTRATOR")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                final HttpSession session = request.getSession();
                final String action = request.getParameter("action");
                final String projectId = request.getParameter("projectId");
                try {
                    try (Connection conn = DBConnection.getConnection()) {
                        if (projectId != null) {
                            final Project project = new ProjectDao(conn).read(Integer.parseInt(projectId));
                            session.setAttribute("project", project);
                            session.setAttribute("managers", new ManagerDao(conn).readAll());
                            session.setAttribute("builds", new BuildDao(conn).readByProject(project.getProjectId()));
                            request.getRequestDispatcher("/content/admin/project/edit-project.jsp").forward(request, response);
                        } else {
                            switch (action) {
                                case "new": {
                                    session.setAttribute("managers", new ManagerDao(conn).readAll());
                                    session.setAttribute("users", new UserDao(conn).readAll());
                                    request.getRequestDispatcher("/content/admin/project/add-project.jsp").forward(request, response);
                                    break;
                                }
                                case "goBack":
                                case "list": {
                                    session.setAttribute("projects", new ProjectDao(conn).readAll());
                                    session.setAttribute("managers", new ManagerDao(conn).readAll());
                                    session.setAttribute("users", new UserDao(conn).readAll());
                                    request.getRequestDispatcher("/content/admin/project/project.jsp").forward(request, response);
                                }
                            }
                        }
                    } catch (SQLException e) {
                        throw new Exception(e);
                    }
                } catch (Exception e) {
                    Logger logger = Logger.getLogger(e.getClass().getName());
                    logger.severe(e.getMessage());
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    private Project getProject(HttpServletRequest request) throws SQLException {
        Project project = new Project();
        final String projectId = request.getParameter("projectId");
        final String manager = request.getParameter("manager");
        if (projectId != null) {
            project.setProjectId(Integer.parseInt(projectId));
        }
        project.setProjectName(request.getParameter("projectName"));
        project.setDescription(request.getParameter("description"));
        if (manager != null) {
            Manager mgr = new Manager();
            mgr.setManagerId(Integer.parseInt(manager));
            project.setManagerId(mgr);
        }
        return project;
    }

    private Build getBuild(HttpServletRequest request) throws SQLException {
        Build build = new Build();
        final String buildId = request.getParameter("buildId");
        if (buildId != null) {
            build.setBuildId(Integer.parseInt(buildId));
        }
        build.setBuild(request.getParameter("build"));
        Project project = new Project();
        project.setProjectId(Integer.parseInt(request.getParameter("project")));
        build.setProjectId(project);
        return build;
    }
}
