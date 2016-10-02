package by.itracker.controller;

import by.itracker.Auth;
import by.itracker.DBConnection;
import by.itracker.dao.StatusDao;
import by.itracker.entity.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "StatusController", urlPatterns = "/content/admin/status")
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
                        new StatusDao(conn, Status.class).update(status);
                        session.setAttribute("servlet", "issue");
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
        final String statusId = request.getParameter("statusId");
        try {
            if (statusId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("status", new StatusDao(conn, Status.class).read(Integer.parseInt(statusId)));
                    request.getRequestDispatcher("/content/admin/status/edit-status.jsp").forward(request, response);
                }
            } else {
                switch (action) {
                    case "goBack":
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("statuses", new StatusDao(conn, Status.class).readAll());
                            request.getRequestDispatcher("/content/admin/status/status.jsp").forward(request, response);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(e.getClass().getName());
            logger.severe(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
//            }
//        }
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
