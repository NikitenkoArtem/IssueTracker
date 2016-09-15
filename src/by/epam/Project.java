package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Project")
public class Project extends HttpServlet {
    private final String insertProject = "INSERT INTO projects VALUES(?, ?, ?, ?)";
    private final String updateProject = "UPDATE projects SET project_name=?, description=?, build=?, manager=?";
    private final String selectProjects = "SELECT project_name, description, manager FROM projects";
    private final String selectManager = "SELECT manager_name FROM managers";
    private final String selectBuilds = "SELECT build FROM projects";
    private final String selectProject = "SELECT project_name, description, build, manager FROM projects WHERE project_name=?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String newProject = request.getParameter("newProject");
        final String add = request.getParameter("add");
        final String edit = request.getParameter("edit");
        try (Connection conn = DBConnection.getConnection()) {
            if (newProject != null) {
                final ArrayList<HashMap<String, Object>> managers = new DBConnection().execQuery(conn, selectManager);
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("/admin/projects/add-project.jsp").forward(request, response);
            } else if (edit != null) {
                updateProject(conn, request, updateProject);
            } else if (add != null) {
                updateProject(conn, request, insertProject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String projectName = request.getParameter("projectName");
        try (Connection conn = DBConnection.getConnection()) {
            if (projectName != null) {
                final String description = request.getParameter("description");
                final String manager = request.getParameter("manager");
                request.setAttribute("description", description);
                request.setAttribute("manager", manager);
//                HashMap<Integer, String> table = new HashMap<>();
//                table.put(1, projectName);
//                try (PreparedStatement stmt = new DBConnection().prepareUpdate(conn, selectProject, table)) {
//                    final ResultSet rs = stmt.executeQuery();
//                }
//                final ArrayList<HashMap<String, Object>> project = new DBConnection().execQuery(conn, selectProject);
                final ArrayList<HashMap<String, Object>> builds = new DBConnection().execQuery(conn, selectBuilds);
//                request.setAttribute("project", project);
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
        final ArrayList<HashMap<String, Object>> projects = new DBConnection().execQuery(conn, selectProjects);
        final ArrayList<HashMap<String, Object>> managers = new DBConnection().execQuery(conn, selectManager);
        request.setAttribute("projects", projects);
        request.setAttribute("managers", managers);
        return request;
    }

    private void updateProject(Connection conn, HttpServletRequest request, String sql) throws SQLException {
        final String name = request.getParameter("name");
        final String desc = request.getParameter("desc");
        final String build = request.getParameter("build");
        final String manager = request.getParameter("manager");
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, name);
        table.put(2, desc);
        table.put(3, build);
        table.put(4, manager);
        new DBConnection().execUpdate(conn, sql, table);
    }
}
