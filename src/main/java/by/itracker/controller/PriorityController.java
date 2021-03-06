package by.itracker.controller;

import by.itracker.Auth;
import by.itracker.DBConnection;
import by.itracker.dao.PriorityDao;
import by.itracker.entity.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "PriorityController", urlPatterns = "/content/admin/priority")
public class PriorityController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        try {
            try (Connection conn = DBConnection.getConnection()) {
                switch (action) {
                    case "add": {
                        Priority priority = getPriority(request);
                        new PriorityDao(conn, Priority.class).create(priority);
                        session.setAttribute("servlet", "priority");
                        response.sendRedirect("/200.jsp");
                        break;
                    }
                    case "edit": {
                        Priority priority = getPriority(request);
                        new PriorityDao(conn, Priority.class).update(priority);
                        session.setAttribute("servlet", "priority");
                        response.sendRedirect("/200.jsp");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        final String priorityId = request.getParameter("priorityId");
        try {
            if (priorityId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("priority", new PriorityDao(conn, Priority.class).read(Integer.parseInt(priorityId)));
                    request.getRequestDispatcher("admin/priority/edit-priority.jsp").forward(request, response);
                }
            } else {
                switch (action) {
                    case "new": {
                        request.getRequestDispatcher("/content/admin/priority/add-priority.jsp").forward(request, response);
                        break;
                    }
                    case "goBack":
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("priorities", new PriorityDao(conn, Priority.class).readAll());
                            request.getRequestDispatcher("/content/admin/priority/priority.jsp").forward(request, response);
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

    private Priority getPriority(HttpServletRequest request) throws SQLException {
        Priority priority = new Priority();
        final String priorityId = request.getParameter("priorityId");
        if (priorityId != null) {
            priority.setPriorityId(Integer.parseInt(priorityId));
        }
        priority.setPriorityName(request.getParameter("priorityName"));
        return priority;
    }
}
