package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Priority")
public class Priority extends HttpServlet {
    private final String insertPriority = "INSERT INTO priority VALUES(?)";
    private final String updatePriority = "UPDATE projects SET priority_name = ?";
    private final String selectPriority = "SELECT priority_name FROM priorities";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            editPriority(conn, request);
            request.getRequestDispatcher("admin/priorities/priority.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            request.setAttribute("statuses", DBConnection.getList(conn, selectPriority));
            request.getRequestDispatcher("admin/priorities/edit-priority.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editPriority(Connection conn, HttpServletRequest request) throws SQLException {
        final String statusName = request.getParameter("status_name");
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, statusName);
        new DBConnection().execUpdate(conn, updatePriority, table);
    }
}
