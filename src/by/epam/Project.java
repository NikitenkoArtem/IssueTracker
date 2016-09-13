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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Project")
public class Project extends HttpServlet {
    private final String insertProject = "INSERT INTO projects VALUES(?, ?, ?, ?)";
    private final String updateProject = "UPDATE projects SET project_name=?, description=?, build=?, manager=?";
    private final String selectProject = "SELECT project_name, manager_name, description FROM projects " +
            "INNER JOIN managers m ON manager = m.manager_id";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String name = request.getParameter("name");
        final String desc = request.getParameter("desc");
        final String build = request.getParameter("build");
        final String manager = request.getParameter("manager");
        HashMap<Integer, String> table = new HashMap<>();
        table.put(1, name);
        table.put(2, desc);
        table.put(3, build);
        table.put(4, manager);
        new DBConnection().execUpdate(insertProject, table);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getParameter("action");
        if (action.equals("list")) {
            try (Connection conn = DBConnection.getConnection()) {
                request.setAttribute("projects", projects(conn));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/admin/projects/project.jsp").forward(request, response);
    }

    private StringBuffer projects(Connection conn) throws SQLException, IOException {
        StringBuffer buffer = null;
        String openTr = "<tr>";
        String closeTr = "</tr>";
        String openTd = "<td>";
        String closeTd = "</td>";
        try (PreparedStatement stmt = conn.prepareStatement(selectProject)) {
            try (ResultSet rs = stmt.executeQuery()) {
                buffer = new StringBuffer();
                while (rs.next()) {
                    final String projectName = rs.getString("project_name");
                    final String managerName = rs.getString("manager_name");
                    final String description = rs.getString("description");
                    buffer.append(openTr);
                    buffer.append(openTd + "<a href='/project?projectName=" + projectName + "'>" + projectName + "</a>" + closeTd);
                    buffer.append(openTd + managerName + closeTd);
                    buffer.append(openTd + description + closeTd);
                    buffer.append(closeTr);
                }
            }
        }
        return buffer;
    }
}
