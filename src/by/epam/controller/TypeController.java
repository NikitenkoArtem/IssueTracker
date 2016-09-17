package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.TypeDao;
import by.epam.entity.Type;

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
@WebServlet(name = "TypeController")
public class TypeController extends HttpServlet {
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
                request.setAttribute("types", new TypeDao(conn).readAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/types/type.jsp").forward(request, response);
        }
    }

    private void addType(Connection conn, String typeName) throws SQLException {
        Type type = new Type();
        type.setTypeName(typeName);
        new TypeDao(conn).create(type);
    }

    private void editType(Connection conn, String[] sqlParams) throws SQLException {
        Type type = new Type();
        type.setTypeName(sqlParams[0]);
        type.setId(Integer.parseInt(sqlParams[1]));
        new TypeDao(conn).update(type);
    }
}
