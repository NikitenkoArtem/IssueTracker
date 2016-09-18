package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.UserDao;
import by.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
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
                        if (pwd.equals(password)) {
                            Cookie cookie = new Cookie(username, username);
                            response.addCookie(cookie);
                            request.setAttribute("username", user.getFirstName());
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
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

    private void loginFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String failed = "Log in failed. Try again";
        request.setAttribute("failed", failed);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
