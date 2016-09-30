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
@WebServlet(name = "IssueController", urlPatterns = "/issue")
public class IssueController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        Cookie userRole = new Auth(request).getCookieByName("userRole");
        final String search = request.getParameter("search");
        try {
            if (search != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    final Issue issue = new IssueDao(conn).read(Integer.parseInt(search));
                    session.setAttribute("issues", issue);
                    getList(session, conn);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } else if (userRole != null) {
                final String role = userRole.getValue();
                if (!role.equals("USER") && !role.equals("ADMINISTRATOR")) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                } else {
                    final String action = request.getParameter("action");
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
            Cookie userRole = new Auth(request).getCookieByName("userRole");
            Principal principal = request.getUserPrincipal();
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
                    final String role = userRole.getValue();
                    if (!role.equals("USER") && !role.equals("ADMINISTRATOR")) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    } else {
                        try (Connection conn = DBConnection.getConnection()) {
                            final Issue issue = new IssueDao(conn).read(Integer.parseInt(issueId));
                            session.setAttribute("issue", issue);
                            session.setAttribute("statuses", new StatusDao(conn).readAll());
                            session.setAttribute("resolutions", new ResolutionDao(conn).readAll());
                            session.setAttribute("types", new TypeDao(conn).readAll());
                            session.setAttribute("priorities", new PriorityDao(conn).readAll());
                            session.setAttribute("projects", new ProjectDao(conn).readAll());
                            session.setAttribute("users", new UserDao(conn).readAll());
                            request.getRequestDispatcher("/content/auth/issue/edit-issue.jsp").forward(request, response);
                        } catch (SQLException e) {
                            throw new Exception(e);
                        }
                    }
                } else {
                    try (Connection conn = DBConnection.getConnection()) {
                        if (action == null) {
                            action = "list";
                        }
                        switch (action) {
                            case "new": {
                                final String role = userRole.getValue();
                                if (!role.equals("USER") && !role.equals("ADMINISTRATOR")) {
                                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                                    break;
                                } else {
                                    session.setAttribute("statuses", new StatusDao(conn).readAll());
                                    session.setAttribute("resolutions", new ResolutionDao(conn).readAll());
                                    session.setAttribute("types", new TypeDao(conn).readAll());
                                    session.setAttribute("priorities", new PriorityDao(conn).readAll());
                                    session.setAttribute("projects", new ProjectDao(conn).readAll());
                                    session.setAttribute("builds", new BuildDao(conn).readAll());
                                    session.setAttribute("users", new UserDao(conn).readAll());
                                    request.getRequestDispatcher("/content/auth/issue/add-issue.jsp").forward(request, response);
                                    break;
                                }
                            }
                            case "goBack":
                            case "list": {
                                session.setAttribute("issues", new IssueDao(conn).readAll());
                                getList(session, conn);
                                request.getRequestDispatcher("/content/auth/issue/issue.jsp").forward(request, response);
                                break;
                            }
                        }
                    } catch (SQLException e) {
                        throw new Exception(e);
                    }
                }
//            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(e.getClass().getName());
            logger.severe(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void getList(HttpSession session, Connection conn) throws SQLException {
        session.setAttribute("statuses", new StatusDao(conn).readAll());
        session.setAttribute("resolutions", new ResolutionDao(conn).readAll());
        session.setAttribute("types", new TypeDao(conn).readAll());
        session.setAttribute("priorities", new PriorityDao(conn).readAll());
        session.setAttribute("projects", new ProjectDao(conn).readAll());
        session.setAttribute("builds", new BuildDao(conn).readAll());
        session.setAttribute("users", new UserDao(conn).readAll());
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
