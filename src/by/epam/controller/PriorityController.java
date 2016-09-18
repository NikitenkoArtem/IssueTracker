package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.PriorityDao;
import by.epam.entity.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "PriorityController")
public class PriorityController extends HttpServlet {
    @Override
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String priorityName = request.getParameter("priorityName");
        if (priorityName != null) {
            request.setAttribute("priorityName", priorityName);
            request.getRequestDispatcher("admin/priorities/edit-priority.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                final List<Priority> priorities = new PriorityDao(conn).readAll();
                request.setAttribute("priorities", priorities);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/priorities/priority.jsp").forward(request, response);
        }
    }

    private void addPriority(Connection conn, String sqlParam) throws SQLException {
        Priority priority = new Priority();
        priority.setPriorityName(sqlParam);
        new PriorityDao(conn).create(priority);
    }

    private void editPriority(Connection conn, String[] sqlParams) throws SQLException {
        Priority priority = new Priority();
        priority.setPriorityId(Integer.parseInt(sqlParams[0]));
        priority.setPriorityName(sqlParams[1]);
        new PriorityDao(conn).update(priority);
    }
}
