package by.epam.controller;

import by.epam.DBConnection;
import by.epam.dao.ResolutionDao;
import by.epam.entity.Resolution;

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
@WebServlet(name = "ResolutionController")
public class ResolutionController extends HttpServlet {
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
                request.setAttribute("resolutions", new ResolutionDao(conn).readAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("admin/resolutions/resolution.jsp").forward(request, response);
        }
    }

    private void addResolution(Connection conn, String resolutionName) throws SQLException {
        Resolution resolution = new Resolution();
        resolution.setResolutionName(resolutionName);
        new ResolutionDao(conn).create(resolution);
    }

    private void editResolution(Connection conn, String[] sqlParams) throws SQLException {
        Resolution resolution = new Resolution();
        resolution.setResolutionName(sqlParams[0]);
        resolution.setId(Integer.parseInt(sqlParams[1]));
        new ResolutionDao(conn).update(resolution);
    }
}
