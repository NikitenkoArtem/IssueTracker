package by.epam;

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
@WebServlet(name = "Priority")
public class Priority extends HttpServlet {
    private final String insertPriority = "INSERT INTO priority VALUES(?)";
    private final String updatePriority = "UPDATE projects SET priority_name = ? WHERE priority_name = ?";
    private final String selectPriorities = "SELECT priority_name FROM priorities";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String addPriority = request.getParameter("addPriority");
        final String added = request.getParameter("added");
        final String edited = request.getParameter("edited");
        final String priorityName = request.getParameter("priorityName");
        if (addPriority != null) {
            request.getRequestDispatcher("admin/priorities/add-priority.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                if (added != null) {
                    addPriority(conn, added);
                }
                if (edited != null && priorityName != null) {
                    editPriority(conn, new String[]{edited, priorityName});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/priorities/priority.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String priorityName = request.getParameter("priorityName");
        if (priorityName != null) {
            request.setAttribute("priorityName", priorityName);
            request.getRequestDispatcher("admin/priorities/edit-priority.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                request.setAttribute("priorities", DBConnection.getList(conn, selectPriorities));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/priorities/priority.jsp").forward(request, response);
        }
    }

    private void addPriority(Connection conn, String sqlParam) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, sqlParam);
        new DBConnection().execUpdate(conn, insertPriority, table);
    }

    private void editPriority(Connection conn, String[] sqlParams) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
//        for (String str : sqlParams) {
        table.put(1, sqlParams[0]);
        table.put(2, sqlParams[1]);
//            table.put(1, edited);
//            table.put(2, resolutionName);
//        }
        new DBConnection().execUpdate(conn, updatePriority, table);
    }
}
