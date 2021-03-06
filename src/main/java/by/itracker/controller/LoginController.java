package by.itracker.controller;

import by.itracker.DBConnection;
import by.itracker.dao.RoleDao;
import by.itracker.dao.UserDao;
import by.itracker.entity.Role;
import by.itracker.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Price on 09.09.2016.
 */
@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        if (username != "" && password != "") {
            try {
                try (Connection conn = DBConnection.getConnection()) {
                    User user = new UserDao(conn, User.class).read(username);
                    final String pwd = user.getPassword();
                    if (pwd != null) {
//                        String sessionId;
                        if (pwd.equals(password)) {
//                            sessionId = new Auth(request).getCookieValue("JSESSIONID");
//                            if (sessionId == null) {
//                                MessageDigest md5 = MessageDigest.getInstance("MD5");
//                                md5.update(username.getBytes());
//                                sessionId = md5.digest().toString();
//                            }
//                            Cookie sessionid = new Cookie("SESSIONID", sessionId);
                            Cookie userName = new Cookie("userName", user.getFirstName());
//                            Role role = new RoleDao(conn, Role.class).read(user.getRoleId().getRoleId());
//                            String roleName = role.getRoleName();

                            Cookie userRole = new Cookie("userRole", user.getRoleId().getRoleName());
                            final int id = user.getUserId();
                            Cookie userId = new Cookie("userId", Integer.toString(id));
//                            sessionid.setPath("/");
//                            sessionid.setMaxAge(1800);
                            userId.setMaxAge(1800);
                            userName.setMaxAge(1800);
                            userRole.setMaxAge(1800);
//                            response.addCookie(sessionid);
                            response.addCookie(userId);
                            response.addCookie(userName);
                            response.addCookie(userRole);
//                            HttpSession session = request.getSession(true);
//                            request.authenticate(response);
//                            request.login(user.getEmail(), user.getPassword());
//                            try {
//                                request.login(username, password);
//                            } catch (ServletException e) {
//                                e.getMessage();
//                            }
                            Principal principal = request.getUserPrincipal();
//                            request.getRequestDispatcher("issue").forward(request, response);
//                            response.sendRedirect("issue");
                            System.out.println(principal);
                        } else {
                            loginFailed(request, response);
                        }
                    } else {
                        loginFailed(request, response);
                    }
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
        request.getRequestDispatcher("content/login.jsp").forward(request, response);
    }

    private void loginFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String failed = "Log in failed. Try again";
        request.setAttribute("failed", failed);
        request.getRequestDispatcher("content/login.jsp").forward(request, response);
    }
}
