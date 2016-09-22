package by.epam.controller;

import by.epam.Auth;
import by.epam.DBConnection;
import by.epam.dao.*;
import by.epam.entity.Issue;
import by.epam.entity.User;

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
@WebServlet(name = "UserController")
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie userRole = new Auth(request).getCookieByName("userRole");
        if (userRole == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            final HttpSession session = request.getSession();
            final String action = request.getParameter("action");
            try {
                try (Connection conn = DBConnection.getConnection()) {
                    switch (action) {
                        case "add": {
                            if (!userRole.getValue().equals("ADMINISTRATOR")) {
                                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                            } else {
                                User user = getUser(request);
                                new UserDao(conn).create(user);
                                break;
                            }
                        }
                        case "edit": {
                            User user = getUser(request);
                            new UserDao(conn).update(user);
                            break;
                        }
                        case "find": {
                            if (!userRole.getValue().equals("ADMINISTRATOR")) {
                                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                            } else {
                                User user = getUser(request);
                                User read = new UserDao(conn).read(user.getUserId());
                                if (read == null) {
                                    read = new UserDao(conn).read(user.getEmail());
                                }
                                session.setAttribute("users", read);
                                request.getRequestDispatcher("/content/admin/user/user.jsp").forward(request, response);
                                break;
                            }
                        }
                        case "pwd": {
                            if (!userRole.getValue().equals("ADMINISTRATOR")) {
                                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                            } else {
                                final int userId = Integer.parseInt(request.getParameter("userId"));
                                User user = new UserDao(conn).read(userId);
                                new UserDao(conn).updatePassword(user);
                                break;
                            }
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie userRole = new Auth(request).getCookieByName("userRole");
        final HttpSession session = request.getSession();
        String action = request.getParameter("action");
        final String userId = request.getParameter("userId");
        if (userRole == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            try {
                if (userId != null) {
                    try (Connection conn = DBConnection.getConnection()) {
                        session.setAttribute("users", new UserDao(conn).read(Integer.parseInt(userId)));
                        session.setAttribute("roles", new RoleDao(conn).readAll());
                        request.getRequestDispatcher("/content/auth/edit-user.jsp").forward(request, response);
                    } catch (SQLException e) {
                        throw new Exception(e);
                    }
                } else {
                    if (!userRole.getValue().equals("ADMINISTRATOR")) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    } else {
                        try (Connection conn = DBConnection.getConnection()) {
                            switch (action) {
                                case "search": {
                                    request.getRequestDispatcher("/content/admin/user/search.jsp").forward(request, response);
                                    break;
                                }
                                case "new": {
                                    session.setAttribute("roles", new RoleDao(conn).readAll());
                                    request.getRequestDispatcher("/content/admin/user/add-user.jsp").forward(request, response);
                                    break;
                                }
                                case "list": {
                                    session.setAttribute("users", new UserDao(conn).readAll());
                                    session.setAttribute("roles", new RoleDao(conn).readAll());
                                    request.getRequestDispatcher("/content/auth/edit-user.jsp").forward(request, response);
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
        if (role != null) {
            user.setRole(Integer.parseInt(role));
        }
        user.setPassword(request.getParameter("password"));
        return user;
    }
}
