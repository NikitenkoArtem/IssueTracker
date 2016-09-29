package by.epam.controller;

import by.epam.Auth;
import by.epam.DBConnection;
import by.epam.dao.RoleDao;
import by.epam.dao.UserDao;
import by.epam.entity.Role;
import by.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Price on 09.09.2016.
 */
@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        if (username != "" && password != "") {
            try {
                try (Connection conn = DBConnection.getConnection()) {
                    User user = new UserDao(conn).read(username);
                    final String pwd = user.getPassword();
                    if (pwd != null) {
                        String sessionId;
                        if (pwd.equals(password)) {
                            sessionId = new Auth(request).getCookieValue("JSESSIONID");
                            if (sessionId == null) {
                                MessageDigest md5 = MessageDigest.getInstance("MD5");
                                md5.update(username.getBytes());
                                sessionId = md5.digest().toString();
                            }
                            Cookie sessionid = new Cookie("SESSIONID", sessionId);
                            Cookie userName = new Cookie("userName", user.getFirstName());
                            Role role = new RoleDao(conn).read(user.getRole());
                            Cookie userRole = new Cookie("userRole", role.getRoleName());
                            final int id = user.getUserId();
                            Cookie userId = new Cookie("userId", Integer.toString(id));
                            sessionid.setPath("/");
                            sessionid.setMaxAge(1800);
                            userId.setMaxAge(1800);
                            userName.setMaxAge(1800);
                            userRole.setMaxAge(1800);
                            response.addCookie(sessionid);
                            response.addCookie(userId);
                            response.addCookie(userName);
                            response.addCookie(userRole);
                            HttpSession session = request.getSession(true);
                            response.sendRedirect("/");
                        } else {
                            loginFailed(request, response);
                        }
                    } else {
                        loginFailed(request, response);
                    }
                } catch (SQLException e) {
                    throw new Exception(e);
                }
            } catch (Exception e) {
                Logger logger = Logger.getLogger(e.getClass().getName());
                logger.severe(e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            loginFailed(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private void loginFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String failed = "Log in failed. Try again";
        request.setAttribute("failed", failed);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
