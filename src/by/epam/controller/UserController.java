package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.UserDao;
import by.epam.entity.User;

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
@WebServlet(name = "UserController")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String action = request.getParameter("action");
        try {
            try (Connection conn = DBConnection.getConnection()) {
                switch (action) {
                    case "add": {
                        User user = getUser(request);
                        new UserDao(conn).create(user);
                        break;
                    }
                    case "edit": {
                        User user = getUser(request);
                        new UserDao(conn).update(user);
                        break;
                    }
                    case "find": {
                        User user = getUser(request);
                        User read = null;
                        read = new UserDao(conn).read(user.getUserId());
                        if (read == null) {
                            read = new UserDao(conn).read(user.getEmail());
                        }
                        session.setAttribute("users", read);
                        request.getRequestDispatcher("/content/admin/user/user.jsp").forward(request, response);
                        break;
                    }
                    case "pwd": {
                        final int userId = Integer.parseInt(request.getParameter("userId"));
                        User user = new UserDao(conn).read(userId);
                        new UserDao(conn).updatePassword(user);
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
        try {
            switch (action) {
                case "search": {
                    request.getRequestDispatcher("/content/admin/user/search.jsp").forward(request, response);
                    break;
                }
                case "new": {
                    request.getRequestDispatcher("/content/admin/user/add-user.jsp").forward(request, response);
                    break;
                }
                case "list": {
                    try (Connection conn = DBConnection.getConnection()) {
                        session.setAttribute("users", new UserDao(conn).readAll());
                        request.getRequestDispatcher("/content/admin/user/user.jsp").forward(request, response);
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

    private User getUser(HttpServletRequest request) {
        User user = new User();
        final String userId = request.getParameter("userId");
        final String role = request.getParameter("role");
        if (userId != null) {
            user.setUserId(Integer.parseInt(userId));
        }
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
//        String[] roles = request.getParameterValues("role");
        if (role != null) {
            user.setRole(Integer.parseInt(role));
        }
        user.setPassword(request.getParameter("password"));
        return user;
    }
}
