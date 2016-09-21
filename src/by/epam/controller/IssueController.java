package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.*;
import by.epam.entity.Issue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "IssueController")
public class IssueController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        try {
            try (Connection conn = DBConnection.getConnection()) {
                switch (action) {
                    case "add": {
                        Issue issue = getIssue(request);
                        new IssueDao(conn).create(issue);
                        session.setAttribute("servlet", "issue");
                        response.sendRedirect("/200.jsp");
                        break;
                    }
                    case "edit": {
                        Issue issue = getIssue(request);
                        new IssueDao(conn).update(issue);
                        session.setAttribute("servlet", "issue");
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
        String action = request.getParameter("action");
        final String issueId = request.getParameter("issueId");
        try {
            if (issueId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    final Issue issue = new IssueDao(conn).read(Integer.parseInt(issueId));
                    session.setAttribute("issue", issue);
                    session.setAttribute("statuses", new StatusDao(conn).readAll());
                    session.setAttribute("resolutions", new ResolutionDao(conn).readAll());
                    session.setAttribute("types", new TypeDao(conn).readAll());
                    session.setAttribute("priorities", new PriorityDao(conn).readAll());
                    session.setAttribute("projects", new ProjectDao(conn).readAll());
                    session.setAttribute("builds", new BuildDao(conn).readByProject(issue.getProject()));
                    session.setAttribute("users", new UserDao(conn).readAll());
                    request.getRequestDispatcher("content/auth/issue/edit-issue.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } else {
                if (action == null) {
                    action = "list";
                }
                switch (action) {
                    case "new": {
                        request.getRequestDispatcher("/content/auth/issue/add-issue.jsp").forward(request, response);
                        break;
                    }
                    case "goBack":
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("issues", new IssueDao(conn).readAll());
                            session.setAttribute("statuses", new StatusDao(conn).readAll());
                            session.setAttribute("resolutions", new ResolutionDao(conn).readAll());
                            session.setAttribute("types", new TypeDao(conn).readAll());
                            session.setAttribute("priorities", new PriorityDao(conn).readAll());
                            session.setAttribute("projects", new ProjectDao(conn).readAll());
//                            session.setAttribute("builds", new BuildDao(conn).readByProject(issue.getProject()));
                            session.setAttribute("users", new UserDao(conn).readAll());
                            request.getRequestDispatcher("index.jsp").forward(request, response);
//                            request.getRequestDispatcher("/content/auth/issue/issue.jsp").forward(request, response);
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

    private Issue getIssue(HttpServletRequest request) throws SQLException {
        Issue issue = new Issue();
        final String issueId = request.getParameter("issueId");
        final String status = request.getParameter("status");
        final String resolution = request.getParameter("resolution");
        final String type = request.getParameter("type");
        final String priority = request.getParameter("priority");
        final String project = request.getParameter("project");
        final String assignee = request.getParameter("assignee");
        if (issueId != null) {
            issue.setIssueId(Integer.parseInt(issueId));
        }
        issue.setSummary(request.getParameter("summary"));
        issue.setDescription(request.getParameter("description"));
        if (status != null) {
            issue.setStatus(Integer.parseInt(status));
        }
        if (resolution != null) {
            issue.setResolution(Integer.parseInt(resolution));
        }
        if (type != null) {
            issue.setType(Integer.parseInt(type));
        }
        if (priority != null) {
            issue.setPriority(Integer.parseInt(priority));
        }
        if (project != null) {
            issue.setProject(Integer.parseInt(project));
        }
        if (assignee != null) {
            issue.setAssignee(Integer.parseInt(assignee));
        }
        return issue;
    }
}
