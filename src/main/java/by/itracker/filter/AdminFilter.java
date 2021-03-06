package by.itracker.filter;

import by.itracker.Auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Price on 30.09.2016.
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = "/content/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final boolean userInRole = new Auth((HttpServletRequest) request).isUserInRole("ADMINISTRATOR");
        if (userInRole) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
