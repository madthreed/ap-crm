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
import java.util.ArrayList;

@WebServlet(name = "StudentController", urlPatterns = "/students")
public class StudentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();

        try {
            ArrayList<Student> students = (ArrayList<Student>) dbServices.getAllActiveStudents();
            req.setAttribute("students", students);
            req.setAttribute("currentPage", "students.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String ids = req.getParameter("deleteStudentHiddenId");
        String[] idsDelete = ids.split(" ");

        DBServices dbServices = new DBServices();

        for (String id : idsDelete) {
            try {
                dbServices.deleteStudentById(id);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("currentPage", "sqlerror.jsp");
                req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
            }
        }

        resp.sendRedirect("/students");
    }
}
