package by.itracker.controller;

import by.itracker.Auth;
import by.itracker.DBConnection;
import by.itracker.dao.ResolutionDao;
import by.itracker.entity.Resolution;

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
@WebServlet(name = "ResolutionController", urlPatterns = "/content/admin/resolution")
public class ResolutionController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        try {
            try (Connection conn = DBConnection.getConnection()) {
                switch (action) {
                    case "add": {
                        Resolution resolution = getResolution(request);
                        new ResolutionDao(conn, Resolution.class).create(resolution);
                        session.setAttribute("servlet", "issue");
                        response.sendRedirect("/200.jsp");
                        break;
                    }
                    case "edit": {
                        Resolution resolution = getResolution(request);
                        new ResolutionDao(conn, Resolution.class).update(resolution);
                        session.setAttribute("servlet", "issue");
                        response.sendRedirect("/200.jsp");
                        break;
                    }
                }
            }
            request.getRequestDispatcher("admin/resolutions/resolution.jsp").forward(request, response);
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
        final String resolutionId = request.getParameter("resolutionId");
        try {
            if (resolutionId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("resolution", new ResolutionDao(conn, Resolution.class).read(Integer.parseInt(resolutionId)));
                    request.getRequestDispatcher("/content/admin/resolution/edit-resolution.jsp").forward(request, response);
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
                            session.setAttribute("resolutions", new ResolutionDao(conn, Resolution.class).readAll());
                            request.getRequestDispatcher("/content/admin/resolution/resolution.jsp").forward(request, response);
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

    private Resolution getResolution(HttpServletRequest request) {
        Resolution resolution = new Resolution();
        final String resolutionId = request.getParameter("resolutionId");
        if (resolutionId != null) {
            resolution.setResolutionId(Integer.parseInt(resolutionId));
        }
        resolution.setResolutionName(request.getParameter("resolutionName"));
        return resolution;
    }
}
