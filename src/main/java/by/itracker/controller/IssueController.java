package by.itracker.controller;

import by.itracker.Auth;
import by.itracker.DBConnection;
import by.itracker.dao.*;
import by.itracker.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.Principal;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "IssueController", urlPatterns = "/content/auth/issue")
public class IssueController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String search = request.getParameter("search");
        try {
            if (search != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    final Issue issue = new IssueDao(conn, Issue.class).read(Integer.parseInt(search));
                    session.setAttribute("issues", issue);
                    getList(session, conn);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                final String action = request.getParameter("action");
                try (Connection conn = DBConnection.getConnection()) {
                    switch (action) {
                        case "add": {
                            Issue issue = getIssue(request);
                            new IssueDao(conn, Issue.class).create(issue);
                            session.setAttribute("servlet", "issue");
                            response.sendRedirect("/200.jsp");
                            break;
                        }
                        case "edit": {
                            Issue issue = getIssue(request);
                            new IssueDao(conn, Issue.class).update(issue);
                            session.setAttribute("servlet", "issue");
                            response.sendRedirect("/200.jsp");
                            break;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final HttpSession session = request.getSession();
//            session.getAttributeNames()
            /*if (userRole == null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("issues", new IssueDao(conn).readAll());
                    getList(session, conn);
                }
                request.setAttribute("isComeBack", true);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {*/
            String action = request.getParameter("action");
            final String issueId = request.getParameter("issueId");
            if (issueId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    final Issue issue = new IssueDao(conn, Issue.class).read(Integer.parseInt(issueId));
                    session.setAttribute("issue", issue);
                    getList(session, conn);
                    request.getRequestDispatcher("/content/auth/issue/edit-issue.jsp").forward(request, response);
                }
            }
            try (Connection conn = DBConnection.getConnection()) {
                if (action == null) {
                    action = "list";
                }
                switch (action) {
                    case "new": {
                        getList(session, conn);
                        session.setAttribute("builds", new BuildDao(conn, Build.class).readAll());
                        request.getRequestDispatcher("/content/auth/issue/add-issue.jsp").forward(request, response);
                        break;
                    }
                    case "goBack":
                    case "list": {
                        session.setAttribute("issues", new IssueDao(conn, Issue.class).readAll());
                        getList(session, conn);
                        session.setAttribute("builds", new BuildDao(conn, Build.class).readAll());
                        request.getRequestDispatcher("/content/auth/issue/issue.jsp").forward(request, response);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(e.getClass().getName());
            logger.severe(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void getList(HttpSession session, Connection conn) {
        session.setAttribute("statuses", new StatusDao(conn, Status.class).readAll());
        session.setAttribute("resolutions", new ResolutionDao(conn, Resolution.class).readAll());
        session.setAttribute("types", new TypeDao(conn, Type.class).readAll());
        session.setAttribute("priorities", new PriorityDao(conn, Priority.class).readAll());
        session.setAttribute("projects", new ProjectDao(conn, Project.class).readAll());
        session.setAttribute("users", new UserDao(conn, User.class).readAll());
    }

    private Issue getIssue(HttpServletRequest request) {
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
            Status issueStatus = new Status();
            issueStatus.setStatusId(Integer.parseInt(status));
            issue.setStatusId(issueStatus);
        }
        if (resolution != null) {
            Resolution issueResolution = new Resolution();
            issueResolution.setResolutionId(Integer.parseInt(resolution));
            issue.setResolutionId(issueResolution);
        }
        if (type != null) {
            Type issueType = new Type();
            issueType.setTypeId(Integer.parseInt(type));
            issue.setTypeId(issueType);
        }
        if (priority != null) {
            Priority issuePriority = new Priority();
            issuePriority.setPriorityId(Integer.parseInt(priority));
            issue.setPriorityId(issuePriority);
        }
        if (project != null) {
            Project issueProject = new Project();
            issueProject.setProjectId(Integer.parseInt(project));
            issue.setProjectId(issueProject);
        }
        if (assignee != null) {
            User user = new User();
            user.setUserId(Integer.parseInt(assignee));
            issue.setAssigneeId(user);
        }
        return issue;
    }
}
