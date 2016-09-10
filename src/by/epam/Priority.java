package by.epam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Price on 07.09.2016.
 */
@WebServlet(name = "Priority")
public class Priority extends HttpServlet {
    private final String addPriority = "insert into projects values(?, ?, ?, ?, ?)";
    private final String editPriority = "update projects set ? = ?";
    private final String priorityList = "select * from projects";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
