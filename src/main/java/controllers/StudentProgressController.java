package controllers;

import database.DBServices;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("progressStudentHiddenId");

        DBServices dbServices = new DBServices();
        Student student = null;

        try {
            student = dbServices.getStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        req.setAttribute("student", student);
        req.setAttribute("currentPage", "student-progress.jsp");
        req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req,resp);
    }
}
