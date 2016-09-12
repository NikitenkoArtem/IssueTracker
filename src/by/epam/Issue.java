package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Issue")
public class Issue extends HttpServlet {
    private final String newIssue = "insert into issues(summary, description, status, type, priority, project, " +
            "build_found, assignee) values(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String changeIssue = "update issues set summary=?, description=?, status=?, resolution=?, type=?, " +
            "priority=?, project=?, build_found=?, assignee=?";
    private final String issues = "SELECT issue_id, p.priority_name, assignee, t.type_name, s.status_name, summary\n" +
            "            FROM issues\n" +
            "            INNER JOIN priorities p ON p.PRIORITY_ID = PRIORITY\n" +
            "            INNER JOIN types t ON t.TYPE_ID = TYPE INNER JOIN statuses s ON s.STATUS_ID = STATUS";

    private final String statuses = "SELECT status_name FROM statuses";
    private final String resolutions = "SELECT resolution_name FROM resolutions";
    private final String priorities = "SELECT priority_name FROM priorities";
    private final String types = "SELECT type_name FROM types";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print("Hello, Issue!");
        writer.print("POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print("GET");
        try (Connection conn = DBConnection.getConnection()) {
            final String action = request.getParameter("action");
            switch (action) {
                case "add":
                    addIssue(conn, request);
                case "edit":
                    editIssue(conn, request);
                case "list":
//                    final ResultSet rs = new DBConnection().getInfo(conn, issues);
                    try (PreparedStatement stmt = conn.prepareStatement(issues)) {
                        ResultSet rs = stmt.executeQuery();

//                        issue_id, p.priority_name, assignee, t.type_name, s.status_name, summary
                        request.setAttribute("issues", rs);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
            }
//            String edit = request.getParameter("issueId");
//            if (!edit.isEmpty()) {
//                int id = Integer.parseInt(edit);
//                request.setAttribute("issueId", id);
//                request.setAttribute("statuses", getInfo(conn, statuses));
//                request.setAttribute("resolutions", getInfo(conn, resolutions));
//                request.setAttribute("priorities", getInfo(conn, priorities));
//                request.setAttribute("types", getInfo(conn, types));
//                request.getRequestDispatcher("/admin/issues/issue.jsp").forward(request, response);
//            }
//            String addIssue = request.getParameter("addIssue");
//            if (!edit.isEmpty()) {
//                boolean add = Boolean.parseBoolean(addIssue);
//                request.setAttribute("addIssue", add);
//                request.getRequestDispatcher("/admin/issues/issue.jsp").forward(request, response);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addIssue(Connection conn, HttpServletRequest request) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(newIssue)) {
            stmt.setString(1, request.getParameter("summary"));
            stmt.setString(2, request.getParameter("desc"));
            stmt.setString(3, request.getParameter("statuses"));
            stmt.setString(4, request.getParameter("type"));
            stmt.setString(5, request.getParameter("priority"));
            stmt.setString(6, request.getParameter("project"));
            stmt.setString(7, request.getParameter("build_found"));
            stmt.setString(8, request.getParameter("assignee"));
            stmt.executeUpdate();
        }
    }

    private void editIssue(Connection conn, HttpServletRequest request) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(changeIssue)) {
            stmt.setString(1, request.getParameter("summary"));
            stmt.setString(2, request.getParameter("desc"));
            stmt.setString(3, request.getParameter("statuses"));
            stmt.setString(4, request.getParameter("resolution"));
            stmt.setString(5, request.getParameter("type"));
            stmt.setString(6, request.getParameter("priority"));
            stmt.setString(7, request.getParameter("project"));
            stmt.setString(8, request.getParameter("build_found"));
            stmt.setString(9, request.getParameter("assignee"));
            stmt.executeUpdate();
        }
    }
}
