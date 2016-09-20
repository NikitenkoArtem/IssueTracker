package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.ResolutionDao;
import by.epam.entity.Resolution;

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
@WebServlet(name = "ResolutionController")
public class ResolutionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
            try {
                try (Connection conn = DBConnection.getConnection()) {
                    switch (action) {
                        case "add": {
                            Resolution resolution = getResolution(request);
                            new ResolutionDao(conn).create(resolution);
                            break;
                        }
                        case "edit": {
                            Resolution resolution = getResolution(request);
                            new ResolutionDao(conn).update(resolution);
                            break;
                        }
                    }
                } catch (SQLException e) {
                    throw new Exception(e);
                }
                request.getRequestDispatcher("admin/resolutions/resolution.jsp").forward(request, response);
            } catch (Exception e) {
                Logger logger = Logger.getLogger(e.getClass().getName());
                logger.severe(e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        final String resolutionId = request.getParameter("resolutionId");
        try {
            if (resolutionId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("resolutionId", new ResolutionDao(conn).read(Integer.parseInt(resolutionId)));
                    request.getRequestDispatcher("/content/admin/resolution/edit-resolution.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } else {
                switch (action) {
                    case "new": {
                        request.getRequestDispatcher("/content/admin/resolution/add-resolution.jsp").forward(request, response);
                        break;
                    }
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("resolutions", new ResolutionDao(conn).readAll());
                            request.getRequestDispatcher("/content/admin/resolution/resolution.jsp").forward(request, response);
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

    private Resolution getResolution(HttpServletRequest request) {
        Resolution resolution = new Resolution();
        resolution.setResolutionId(Integer.parseInt(request.getParameter("resolutionId")));
        resolution.setResolutionName(request.getParameter("resolutionName"));
        return resolution;
    }
}
