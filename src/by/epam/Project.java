package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Project")
public class Project extends HttpServlet {
    private final String newProject = "insert into projects values(?, ?, ?, ?)";
    private final String changeProject = "update projects set project_name=?, description=?, build=?, manager=?";
    private final String projectList = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
//            request.setAttribute("projects", getInfo(conn, projectList));
            request.getRequestDispatcher("/admin/projects/project.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProject(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(newProject)) {
                stmt.setString(1, request.getParameter("proj_name"));
                stmt.setString(2, request.getParameter("desc"));
                stmt.setString(3, request.getParameter("build"));
                stmt.setString(4, request.getParameter("manager"));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editProject(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(changeProject)) {
//                stmt.setString(1, request.getParameter("proj_name"));
//                stmt.setString(2, request.getParameter("desc"));
//                stmt.setString(3, request.getParameter("build"));
//                stmt.setString(4, request.getParameter("manager"));
//                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
