package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Price on 09.09.2016.
 */
@WebServlet(name = "LoginCheck")
public class LoginCheck extends HttpServlet {
    private final String credentials = "select email, password from users WHERE email = ? AND password = ?";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            final String username = request.getParameter("username");
            final String password = request.getParameter("password");
            try (PreparedStatement stmt = conn.prepareStatement(credentials)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                final ResultSet rs = stmt.executeQuery();
                if (rs != null) {
                    request.getRequestDispatcher("/").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
