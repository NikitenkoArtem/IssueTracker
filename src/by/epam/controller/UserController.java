package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.UserDao;
import by.epam.entity.User;

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
@WebServlet(name = "UserController")
public class UserController extends HttpServlet {
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
                request.setAttribute("users", new UserDao(conn).readAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/users/user.jsp").forward(request, response);
        }
    }

    private void changePassword(Connection conn, String[] sqlParams) throws SQLException {
        User user = new User();
        user.setEmail(sqlParams[0]);
        user.setPassword(sqlParams[1]);
        new UserDao(conn).updatePassword(user);
    }

    private void addUser(Connection conn, String[] sqlParams) throws SQLException {
        User user = new User();
        user.setEmail(sqlParams[0]);
        user.setFirstName(sqlParams[1]);
        user.setLastName(sqlParams[2]);
        user.setRole(sqlParams[3]);
        user.setPassword(sqlParams[4]);
        new UserDao(conn).create(user);
    }

    private void editUser(Connection conn, String[] sqlParams) throws SQLException {
        User user = new User();
        user.setEmail(sqlParams[0]);
        user.setFirstName(sqlParams[1]);
        user.setLastName(sqlParams[2]);
        user.setRole(sqlParams[3]);
        new UserDao(conn).update(user);
    }
}
