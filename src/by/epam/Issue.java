package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Issue")
public class Issue extends HttpServlet {
    private final String insertIssues = "insert into issues(summary, description, status, type, priority, project, " +
            "build_found, assignee) values(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateIssue = "update issues set summary=?, description=?, status=?, resolution=?, type=?, " +
            "priority=?, project=?, build_found=?, assignee=?";
    private final String selectIssues = "SELECT issue_id, priority_name, assignee, type_name, status_name, summary FROM issues";

    private final String selectStatuses = "SELECT status_name FROM statuses";
    private final String selectResolutions = "SELECT resolution_name FROM resolutions";
    private final String selectPriorities = "SELECT priority_name FROM priorities";
    private final String selectTypes = "SELECT type_name FROM types";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print("POST");
        try (Connection conn = DBConnection.getConnection()) {
            String action = request.getParameter("action");
//            String issueId = request.getParameter("issueId");
//            if (action.equals("add")) {
//                addIssue(conn, request);
//            }
//            if (action.equals("issueId")) {
//                editIssue(conn, request);
//                final String issueId = rs.getString("issue_id");
//                final String priorityName = rs.getString("priority_name");
//                final String assignee = rs.getString("assignee");
//                final String typeName = rs.getString("type_name");
//                final String statusName = rs.getString("status_name");
//                final String summary = rs.getString("summary");
                request.setAttribute("statuses", new DBConnection().execQuery(conn, selectStatuses));
                request.setAttribute("resolutions", new DBConnection().execQuery(conn, selectResolutions));
                request.setAttribute("priorities", new DBConnection().execQuery(conn, selectPriorities));
                request.setAttribute("types", new DBConnection().execQuery(conn, selectTypes));
                request.getRequestDispatcher("/admin/issues/issue.jsp").forward(request, response);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print("GET");
        doPost(request, response);
//        try (Connection conn = DBConnection.getConnection()) {
//            String action = request.getParameter("action");
//            String issueId = request.getParameter("issueId");
//            if (!issueId.isEmpty()) {
//                doPost(request, response);
//            }
//            switch (action) {
////                case "add":
////                case "list":
////                case "":
////                default:
////                    final StringBuffer buffer = issues(conn, response);
////                    request.setAttribute("selectIssues", buffer);
////                    request.getRequestDispatcher("index.jsp").forward(request, response);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    private void addIssue(Connection conn, HttpServletRequest request) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, request.getParameter("summary"));
        table.put(2, request.getParameter("desc"));
        table.put(3, request.getParameter("statuses"));
        table.put(4, request.getParameter("type"));
        table.put(5, request.getParameter("priority"));
        table.put(6, request.getParameter("project"));
        table.put(7, request.getParameter("build_found"));
        table.put(8, request.getParameter("assignee"));
        new DBConnection().execUpdate(conn, insertIssues, table);
    }

    private void editIssue(Connection conn, HttpServletRequest request) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(updateIssue)) {
            stmt.setString(1, request.getParameter("summary"));
            stmt.setString(2, request.getParameter("desc"));
            stmt.setString(3, request.getParameter("selectStatuses"));
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
