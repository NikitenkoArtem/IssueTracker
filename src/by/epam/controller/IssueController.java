package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.*;
import by.epam.entity.Issue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "IssueController")
public class IssueController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String addPriority = request.getParameter("addPriority");
        final String added = request.getParameter("added");
        final String edited = request.getParameter("edited");
        final String priorityName = request.getParameter("priorityName");

        final String issueId = request.getParameter("issue_id");
        final String status = request.getParameter("status");
        final String summary = request.getParameter("summary");
        final String description = request.getParameter("description");
        final String statuses = request.getParameter("statuses");
        final String resolution = request.getParameter("resolution");
        final String type = request.getParameter("type");
        final String priority = request.getParameter("priority");
        final String project = request.getParameter("project");
        final String build = request.getParameter("build");
        final String assignee = request.getParameter("assignee");


//        String[] role = request.getParameterValues("role");
        if (addPriority != null) {
            request.getRequestDispatcher("admin/issues/add-issue.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                if (added != null) {
                    addIssue(conn, new String[] {summary, description, status, type, priority, project});
                }
                if (edited != null && priorityName != null) {
                    editIssue(conn, new String[] {summary, description, status, resolution, type, priority, project, build, assignee});
                }
                request.setAttribute("statuses", new StatusDao(conn).readAll());
                request.setAttribute("resolutions", new ResolutionDao(conn).readAll());
                request.setAttribute("priorities", new PriorityDao(conn).readAll());
                request.setAttribute("types", new TypeDao(conn).readAll());
                request.getRequestDispatcher("/admin/issues/issue.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/users/user.jsp").forward(request, response);
        }
//        try (Connection conn = DBConnection.getConnection()) {
//            String action = request.getParameter("action");
//            String issueId = request.getParameter("issueId");
//            if (action.equals("add")) {
//                addIssue(conn, request);
//            }
//            if (action.equals("issueId")) {
//                editIssue(conn, request);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String priorityName = request.getParameter("priorityName");
        if (priorityName != null) {
            request.setAttribute("priorityName", priorityName);
            request.getRequestDispatcher("admin/users/edit-user.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                request.setAttribute("users", new IssueDao(conn).readAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/users/user.jsp").forward(request, response);
        }
    }

    private void addIssue(Connection conn, String[] sqlParams) throws SQLException {
        Issue issue = new Issue();
        issue.setSummary(sqlParams[0]);
        issue.setDescription(sqlParams[1]);
        issue.setStatus(sqlParams[2]);
        issue.setType(sqlParams[3]);
        issue.setPriority(sqlParams[4]);
        issue.setProject(sqlParams[5]);
        issue.setBuild(sqlParams[6]);
        issue.setAssignee(sqlParams[7]);
        new IssueDao(conn).create(issue);
    }

    private void editIssue(Connection conn, String[] sqlParams) throws SQLException {
        Issue issue = new Issue();
        issue.setSummary(sqlParams[0]);
        issue.setDescription(sqlParams[1]);
        issue.setStatus(sqlParams[2]);
        issue.setResolution(sqlParams[3]);
        issue.setType(sqlParams[4]);
        issue.setPriority(sqlParams[5]);
        issue.setProject(sqlParams[6]);
        issue.setBuild(sqlParams[7]);
        issue.setAssignee(sqlParams[8]);
        new IssueDao(conn).update(issue);
    }
}