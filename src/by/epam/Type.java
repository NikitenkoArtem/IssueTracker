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
@WebServlet(name = "Type")
public class Type extends HttpServlet {
    private final String insertType = "INSERT INTO types VALUES(?)";
    private final String updateType = "UPDATE types SET type_name = ? WHERE type_name = ?";
    private final String selectTypes = "SELECT type_name FROM types";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String addType = request.getParameter("addType");
        final String added = request.getParameter("added");
        final String edited = request.getParameter("edited");
        final String typeName = request.getParameter("typeName");
        if (addType != null) {
            request.getRequestDispatcher("admin/types/add-type.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                if (added != null) {
                    addType(conn, added);
                }
                if (edited != null && typeName != null) {
                    editType(conn, new String[]{edited, typeName});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/types/type.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String typeName = request.getParameter("typeName");
        if (typeName != null) {
            request.setAttribute("typeName", typeName);
            request.getRequestDispatcher("admin/types/edit-type.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                request.setAttribute("types", DBConnection.getList(conn, selectTypes));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/types/type.jsp").forward(request, response);
        }
    }

    private void addType(Connection conn, String sqlParam) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, sqlParam);
        new DBConnection().execUpdate(conn, insertType, table);
    }

    private void editType(Connection conn, String[] sqlParams) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
//        for (String str : sqlParams) {
        table.put(1, sqlParams[0]);
        table.put(2, sqlParams[1]);
//            table.put(1, edited);
//            table.put(2, resolutionName);
//        }
        new DBConnection().execUpdate(conn, updateType, table);
    }
}
