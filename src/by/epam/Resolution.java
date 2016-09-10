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
@WebServlet(name = "Resolution")
public class Resolution extends HttpServlet {
    private final String addResolution = "insert into projects values(?, ?, ?, ?, ?)";
    private final String editResolution = "update projects set ? = ?";
    private final String resolutionList = "select * from projects";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
