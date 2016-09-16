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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Status")
public class Status extends HttpServlet {
    private final String updateStatuses = "UPDATE statuses SET status_name = ? WHERE status_name = ?";
    private final String selectStatuses = "SELECT status_name FROM statuses";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            editStatus(conn, request);
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
            request.setAttribute("statuses", DBConnection.getList(conn, selectStatuses));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("admin/statuses/status.jsp").forward(request, response);
    }

    private void editStatus(Connection conn, HttpServletRequest request) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        final String statusName = request.getParameter("statusName");
        final String newStatus = request.getParameter("newStatus");
        table.put(1, newStatus);
        table.put(2, statusName);
        new DBConnection().execUpdate(conn, updateStatuses, table);
    }
}
