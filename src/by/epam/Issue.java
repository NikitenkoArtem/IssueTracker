package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Issue")
public class Issue extends HttpServlet {
    private final String newIssue = "insert into issues(summary, description, status, type, priority, project, " +
            "build_found, assignee) values(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String changeIssue = "update issues set summary=?, description=?, status=?, resolution=?, type=?, " +
            "priority=?, project=?, build_found=?, assignee=?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void addIssue(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(newIssue)) {
                stmt.setString(1, request.getParameter("summary"));
                stmt.setString(2, request.getParameter("desc"));
                stmt.setString(3, request.getParameter("status"));
                stmt.setString(4, request.getParameter("type"));
                stmt.setString(5, request.getParameter("priority"));
                stmt.setString(6, request.getParameter("project"));
                stmt.setString(7, request.getParameter("build_found"));
                stmt.setString(8, request.getParameter("assignee"));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editIssue(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(changeIssue)) {
                stmt.setString(1, request.getParameter("summary"));
                stmt.setString(2, request.getParameter("desc"));
                stmt.setString(3, request.getParameter("status"));
                stmt.setString(4, request.getParameter("resolution"));
                stmt.setString(5, request.getParameter("type"));
                stmt.setString(6, request.getParameter("priority"));
                stmt.setString(7, request.getParameter("project"));
                stmt.setString(8, request.getParameter("build_found"));
                stmt.setString(9, request.getParameter("assignee"));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
