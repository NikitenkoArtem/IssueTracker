package by.itracker.filter;

import by.itracker.Auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Price on 02.10.2016.
 */
@WebFilter(filterName = "UserFilter", urlPatterns = "/content/auth/*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        final boolean userInRole = new Auth((HttpServletRequest) request).isUserInRole("USER");
        final boolean userInAdminRole = new Auth((HttpServletRequest) request).isUserInRole("ADMINISTRATOR");
        if (userInRole || userInAdminRole) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
