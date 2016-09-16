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
@WebServlet(name = "Resolution")
public class Resolution extends HttpServlet {
    private final String insertResolution = "INSERT INTO resolutions VALUES(?)";
    private final String updateResolution = "UPDATE resolutions SET resolution_name = ? WHERE resolution_name = ?";
    private final String selectResolutions = "SELECT resolution_name FROM resolutions";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String addResolution = request.getParameter("addResolution");
        final String added = request.getParameter("added");
        final String edited = request.getParameter("edited");
        final String resolutionName = request.getParameter("resolutionName");
        if (addResolution != null) {
            request.getRequestDispatcher("admin/resolutions/add-resolution.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                if (added != null) {
                    addResolution(conn, added);
                }
                if (edited != null && resolutionName != null) {
                    editResolution(conn, new String[]{edited, resolutionName});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/resolutions/resolution.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String resolutionName = request.getParameter("resolutionName");
        if (resolutionName != null) {
            request.setAttribute("resolutionName", resolutionName);
            request.getRequestDispatcher("admin/resolutions/edit-resolution.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                request.setAttribute("resolutions", DBConnection.getList(conn, selectResolutions));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/resolutions/resolution.jsp").forward(request, response);
        }
    }

    private void addResolution(Connection conn, String sqlParam) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, sqlParam);
        new DBConnection().execUpdate(conn, insertResolution, table);
    }

    private void editResolution(Connection conn, String[] sqlParams) throws SQLException {
        HashMap<Integer, String> table = new HashMap<>();
//        for (String str : sqlParams) {
            table.put(1, sqlParams[0]);
            table.put(2, sqlParams[1]);
//            table.put(1, edited);
//            table.put(2, resolutionName);
//        }
        new DBConnection().execUpdate(conn, updateResolution, table);
    }
}
