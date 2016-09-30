package by.itracker.controller;

import by.itracker.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Price on 18.09.2016.
 */
@WebServlet(name = "LogoutController", urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cookie login = new Auth(request).getCookieByName("SESSIONID");
            Cookie userName = new Auth(request).getCookieByName("userName");
            Cookie userRole = new Auth(request).getCookieByName("userRole");
            Cookie userId = new Auth(request).getCookieByName("userId");
            if (login != null) {
                login.setMaxAge(0);
                response.addCookie(login);
            }
            if (userName != null) {
                userName.setMaxAge(0);
                response.addCookie(userName);
            }
            if (userRole != null) {
                userRole.setMaxAge(0);
                response.addCookie(userRole);
            }
            if (userId != null) {
                userId.setMaxAge(0);
                response.addCookie(userId);
            }
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(e.getClass().getName());
            logger.severe(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
