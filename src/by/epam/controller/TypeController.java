package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.TypeDao;
import by.epam.entity.Type;

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
@WebServlet(name = "TypeController")
public class TypeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        try {
            try (Connection conn = DBConnection.getConnection()) {
                switch (action) {
                    case "add": {
                        Type type = getType(request);
                        new TypeDao(conn).create(type);
                        session.setAttribute("servlet", type);
                        response.sendRedirect("/200.jsp");
                        break;
                    }
                    case "edit": {
                        Type type = getType(request);
                        new TypeDao(conn).update(type);
                        session.setAttribute("servlet", "type");
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
        final String typeId = request.getParameter("typeId");
        final String action = request.getParameter("action");
        try {
            if (typeId != null) {
                try (Connection conn = DBConnection.getConnection()) {
                    session.setAttribute("type", new TypeDao(conn).read(Integer.parseInt(typeId)));
                    request.getRequestDispatcher("/content/admin/type/edit-type.jsp").forward(request, response);
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } else {
                switch (action) {
                    case "new": {
                        request.getRequestDispatcher("/content/admin/type/add-type.jsp").forward(request, response);
                        break;
                    }
                    case "goBack":
                    case "list": {
                        try (Connection conn = DBConnection.getConnection()) {
                            session.setAttribute("types", new TypeDao(conn).readAll());
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

    private Type getType(HttpServletRequest request) {
        Type type = new Type();
        final String typeId = request.getParameter("typeId");
        if (typeId != null) {
            type.setTypeId(Integer.parseInt(typeId));
        }
        type.setTypeName(request.getParameter("typeName"));
        return type;
    }
}
