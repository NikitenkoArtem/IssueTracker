package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.BuildDao;
import by.epam.dao.ManagerDao;
import by.epam.dao.ProjectDao;
import by.epam.entity.Build;
import by.epam.entity.Manager;
import by.epam.entity.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "ProjectController")
public class ProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String newProject = request.getParameter("newProject");
        final String add = request.getParameter("add");
        final String edit = request.getParameter("edit");
        try (Connection conn = DBConnection.getConnection()) {
            final String name = request.getParameter("name");
            final String desc = request.getParameter("desc");
            final String build = request.getParameter("build");
            final String manager = request.getParameter("manager");
            if (newProject != null) {
                final List<Manager> managers = new ManagerDao(conn).readAll();
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("/admin/projects/add-project.jsp").forward(request, response);
            } else if (edit != null) {
                updateProject(conn, new String[] {name, desc, build, manager});
            } else if (add != null) {
                updateProject(conn, new String[] {name, desc, build, manager});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String projectName = request.getParameter("projectName");
        try (Connection conn = DBConnection.getConnection()) {
            if (projectName != null) {
                final String description = request.getParameter("description");
                final String manager = request.getParameter("manager");
                request.setAttribute("description", description);
                request.setAttribute("manager", manager);

                final List<Build> builds = new BuildDao(conn).readAll();
                request.setAttribute("builds", builds);
                request.getRequestDispatcher("/admin/projects/edit-project.jsp").forward(request, response);
            } else {
                getProjects(conn, request);
                request.getRequestDispatcher("/admin/projects/project.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private HttpServletRequest getProjects(Connection conn, HttpServletRequest request) throws SQLException {
        final List<Project> projects = new ProjectDao(conn).readAll();
        final List<Manager> managers = new ManagerDao(conn).readAll();
        request.setAttribute("projects", projects);
        request.setAttribute("managers", managers);
        return request;
    }

    private void updateProject(Connection conn, String[] params) throws SQLException {
        Project project = new Project();
        project.setProjectName(params[0]);
        project.setDescription(params[1]);
        project.setManager(Integer.parseInt(params[2]));
        new ProjectDao(conn).update(project);
    }

    private void editProject(Connection conn, String[] params) throws SQLException {
        Project project = new Project();
        project.setProjectName(params[0]);
        project.setDescription(params[1]);
        project.setManager(Integer.parseInt(params[2]));
        new ProjectDao(conn).create(project);
    }
}
