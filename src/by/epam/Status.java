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

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Status")
public class Status extends HttpServlet {
    private final String changeStatus = "update projects set status_name = ?";
    private final String statusList = "select * from statuses";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void editStatus(HttpServletRequest request) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(changeStatus)) {
                stmt.setString(1, request.getParameter("status_name"));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getStatusList(HttpServletRequest request) throws SQLException {
    }
}
