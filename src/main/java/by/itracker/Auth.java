package by.itracker;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Price on 19.09.2016.
 */
public class Auth {
    private HttpServletRequest request;

    public Auth(HttpServletRequest request) {
        this.request = request;
    }

    public Cookie getCookieByName(String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                final String name = cookie.getName();
                if (name.equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public String getCookieValue(String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals(cookieName)) {
                    cookieName = cookie.getValue();
                    return cookieName;
                }
            }
        }
        return null;
    }

    public boolean isUserInRole(String role) {
        Cookie userRole = getCookieByName("userRole");
        if (userRole != null) {
            String roleName = userRole.getValue();
            if (roleName.equals(role)) {
                return true;
            }
        }
        return false;
    }
}
