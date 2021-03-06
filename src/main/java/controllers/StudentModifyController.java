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

/**
 * Created by MadThreeD on 2022.
 */

@WebServlet(name = "StudentModifyController", urlPatterns = "/student-modify")
public class StudentModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyStudentHiddenId");

        DBServices dbServices = new DBServices();

        try {
            Student student = dbServices.getStudentById(id);

            req.setAttribute("student", student);
            req.setAttribute("currentPage", "student-modify.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String date = req.getParameter("date");

        DBServices dbServices = new DBServices();

        try {
            dbServices.modifyStudentById(id, surname, name, group, date);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("message", "Вернитесь на предыдущую страницу и внимательно проверьте поля ввода, особенно дату))");
            req.setAttribute("currentPage", "error.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
        resp.sendRedirect("/students");
    }
}