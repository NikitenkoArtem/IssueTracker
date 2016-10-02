package by.itracker.controller;

import by.itracker.Auth;
import by.itracker.DBConnection;
import by.itracker.dao.*;
import by.itracker.entity.Role;
import by.itracker.entity.User;

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
@WebServlet(name = "UserController", urlPatterns = "/user")
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
                                new UserDao(conn, User.class).create(user);
                                break;
                            }
                        }
                        case "edit": {
                            User user = getUser(request);
                            new UserDao(conn, User.class).update(user);
                            break;
                        }
                        case "find": {
                            if (!userRole.getValue().equals("ADMINISTRATOR")) {
                                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                            } else {
                                User user = getUser(request);
                                User read = new UserDao(conn, User.class).read(user.getUserId());
                                if (read == null) {
                                    read = new UserDao(conn, User.class).read(user.getEmail());
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
                                User user = new UserDao(conn, User.class).read(userId);
                                new UserDao(conn, User.class).updatePassword(user);
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
                        session.setAttribute("users", new UserDao(conn, User.class).read(Integer.parseInt(userId)));
                        session.setAttribute("roles", new RoleDao(conn, Role.class).readAll());
                        request.getRequestDispatcher("/content/auth/edit-user.jsp").forward(request, response);
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
                                    session.setAttribute("roles", new RoleDao(conn, Role.class).readAll());
                                    request.getRequestDispatcher("/content/admin/user/add-user.jsp").forward(request, response);
                                    break;
                                }
                                case "list": {
                                    session.setAttribute("users", new UserDao(conn, User.class).readAll());
                                    request.getRequestDispatcher("/content/admin/user/user.jsp").forward(request, response);
                                }
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
            Role userRole = new Role();
            userRole.setRoleId(Integer.parseInt(role));
            user.setRoleId(userRole);
        }
        user.setPassword(request.getParameter("password"));
        return user;
    }
}
