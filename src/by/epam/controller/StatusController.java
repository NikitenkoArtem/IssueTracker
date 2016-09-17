package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.StatusDao;
import by.epam.entity.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "StatusController")
public class StatusController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            final String statusName = request.getParameter("statusName");
            final String newStatus = request.getParameter("statusName");
            editStatus(conn, new String[]{newStatus, statusName});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("admin/statuses/status.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String statusName = request.getParameter("statusName");
        if (statusName != null) {
            request.setAttribute("statusName", statusName);
            request.getRequestDispatcher("admin/statuses/edit-status.jsp").forward(request, response);
        }
        try (Connection conn = DBConnection.getConnection()) {
            request.setAttribute("statuses", new StatusDao(conn).readAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("admin/statuses/status.jsp").forward(request, response);
    }

    private void editStatus(Connection conn, String[] sqlParams) throws SQLException {
        Status status = new Status();
        status.setStatusName(sqlParams[0]);
        status.setId(Integer.parseInt(sqlParams[1]));
        new StatusDao(conn).update(status);
    }
}
