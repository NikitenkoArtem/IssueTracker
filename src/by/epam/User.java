package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "User")
public class User extends HttpServlet {
    private final String insertUser = "INSERT INTO users VALUES(?, ?, ?, 1, ?)";
    private final String updatePassword = "UPDATE users SET password = ? WHERE email = ?";
    private final String updateUser = "UPDATE users SET ? = ?";
    private final String selectUsers = "SELECT * FROM users";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String addPriority = request.getParameter("addPriority");
        final String added = request.getParameter("added");
        final String edited = request.getParameter("edited");
        final String priorityName = request.getParameter("priorityName");

        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String role = request.getParameter("role");
//        String[] role = request.getParameterValues("role");
        if (addPriority != null) {
            request.getRequestDispatcher("admin/users/add-user.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                if (added != null) {
//                    addUser(conn, added);
                    addUser(conn, new String[] {email, firstName, lastName, role, password});
                }
                if (edited != null && priorityName != null) {
                    editUser(conn, new String[] {email, firstName, lastName, role});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/users/user.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String priorityName = request.getParameter("priorityName");
        if (priorityName != null) {
            request.setAttribute("priorityName", priorityName);
            request.getRequestDispatcher("admin/users/edit-user.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                request.setAttribute("users", DBConnection.getList(conn, selectUsers));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/users/user.jsp").forward(request, response);
        }
    }

    private void changePassword(Connection conn, String[] sqlParams) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, sqlParams[0]);
        table.put(2, sqlParams[1]);
        new DBConnection().execUpdate(conn, updatePassword, table);
    }

    private void addUser(Connection conn, String[] sqlParams) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, sqlParams[0]);
        table.put(2, sqlParams[1]);
        table.put(3, sqlParams[2]);
        table.put(4, sqlParams[3]);
        table.put(5, sqlParams[4]);
        new DBConnection().execUpdate(conn, insertUser, table);
    }

    private void editUser(Connection conn, String[] sqlParams) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, sqlParams[0]);
        table.put(2, sqlParams[1]);
        table.put(3, sqlParams[2]);
        table.put(4, sqlParams[3]);
        new DBConnection().execUpdate(conn, updateUser, table);
    }
}
