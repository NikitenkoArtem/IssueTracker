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
@WebServlet(name = "Type")
public class Type extends HttpServlet {
    private final String addType = "insert into projects values(?, ?, ?, ?, ?)";
    private final String editType = "update projects set ? = ?";
    private final String typeList = "select * from projects";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
