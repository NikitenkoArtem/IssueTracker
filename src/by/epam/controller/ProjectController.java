package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.BuildDao;
import by.epam.dao.ManagerDao;
import by.epam.dao.ProjectDao;
import by.epam.dao.UserDao;
import by.epam.entity.Build;
import by.epam.entity.Manager;
import by.epam.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "ProjectController")
public class ProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        final String projectId = request.getParameter("projectId");
        try {
            if (projectId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    final Project project = new ProjectDao(conn).read(Integer.parseInt(projectId));
                    session.setAttribute("project", project);
                    session.setAttribute("managers", new ManagerDao(conn).readAll());
                    session.setAttribute("builds", new BuildDao(conn).readByProject(project.getProjectId()));
                    request.getRequestDispatcher("/content/admin/project/edit-project.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } else {
                switch (action) {
                    case "new": {
                    request.getRequestDispatcher("/content/admin/project/add-project.jsp").forward(request, response);
                        break;
                    }
                    case "goBack":
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("projects", new ProjectDao(conn).readAll());
                            session.setAttribute("users", new UserDao(conn).readAll());
                            session.setAttribute("managers", new ManagerDao(conn).readAll());
                            request.getRequestDispatcher("/content/admin/project/project.jsp").forward(request, response);
                        } catch (SQLException e) {
                            throw new Exception(e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(e.getClass().getName());
            logger.severe(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
            project.setManager(Integer.parseInt(manager));
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
        build.setProject(Integer.parseInt(request.getParameter("project")));
        return build;
    }
}
