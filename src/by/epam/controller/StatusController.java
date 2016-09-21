package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.StatusDao;
import by.epam.entity.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "StatusController")
public class StatusController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        try {
            try (Connection conn = DBConnection.getConnection()) {
                switch (action) {
                    case "edit": {
                        Status status = getStatus(request);
                        new StatusDao(conn).update(status);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        final String statusId = request.getParameter("statusId");
        try {
            if (statusId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("status", new StatusDao(conn).read(Integer.parseInt(statusId)));
                    request.getRequestDispatcher("/content/admin/status/edit-status.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } else {
                switch (action) {
                    case "new": {
                        request.getRequestDispatcher("/content/admin/resolution/add-resolution.jsp").forward(request, response);
                        break;
                    }
                    case "goBack":
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("statuses", new StatusDao(conn).readAll());
                            request.getRequestDispatcher("/content/admin/status/status.jsp").forward(request, response);
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

    private Status getStatus(HttpServletRequest request) {
        Status status = new Status();
        final String statusId = request.getParameter("statusId");
        if (statusId != null) {
            status.setStatusId(Integer.parseInt(statusId));
        }
        status.setStatusName(request.getParameter("statusName"));
        return status;
    }
}
