package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "User")
public class User extends HttpServlet {
    private final String addUser = "insert into users values(?, ?, ?, 1, ?)";
    private final String password = "update users set password = ?";
    private final String editUser = "update users set ? = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        addUser(request);
//        changePassword(request);
        PrintWriter writer = response.getWriter();
        final String str = "Hello, world!";
        writer.print(str);
        HttpSession session = request.getSession();
        session.setAttribute(str, str);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void changePassword(HttpServletRequest request) {
        PreparedStatement stmt = null;
        try (Connection conn = DBConnection.getConnection()) {
            stmt = conn.prepareStatement(password);
            final String pwd = request.getParameter("pwd");
            stmt.setString(1, pwd);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUser(HttpServletRequest request) {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(addUser)) {
                final String first_name = request.getParameter("first-name");
                stmt.setString(2, first_name);
                final String last_name = request.getParameter("last-name");
                stmt.setString(3, last_name);
                final String email = request.getParameter("email");
                stmt.setString(1, email);
                final String pwd = request.getParameter("pwd");
                stmt.setString(4, pwd);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] options = request.getParameterValues("options");
        DBConnection db = new DBConnection();
        try (Connection conn = DBConnection.getConnection()) {
//            executeUpdate(conn, addUser);

            if (options != null) {
                for (String selectedOption : options) {
                    if (selectedOption.equals("add")) {
                        db.executeUpdate(conn, addUser);
                    }
                    if (selectedOption.equals("changepwd")) {
                        db.executeUpdate(conn, password);
                    }
                    if (selectedOption.equals("edituser")) {
                        db.executeUpdate(conn, editUser);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
