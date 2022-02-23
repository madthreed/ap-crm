package controllers;

import database.DBServices;
import entity.Discipline;
import entity.Mark;
import entity.Student;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "StudentProgressEditController", urlPatterns = "/student-progress-edit")
public class StudentProgressEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();

        String studentId = req.getParameter("studentId");
        String termId = req.getParameter("termId");


        try {
            Student student = dbServices.getStudentById(studentId);
            req.setAttribute("student", student);

            List<Discipline> disciplinesByTerm = dbServices.getDisciplinesByTerm(termId);
            List<Mark> marksByStudentAndTermId = dbServices.getMarksByStudentAndTermId(studentId, termId);

            Map<Discipline, Mark> markMap = new HashMap<>();

            for (Discipline discipline : disciplinesByTerm) {


            }


            req.setAttribute("currentPage", "student-progress-edit.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

//        List<Mark> marks;
//        Term selectedTerm;
//
//
//        try {
//            String studentId = req.getParameter("progressStudentHiddenId");
//            Student student = dbServices.getStudentById(studentId);
//
//            List<Term> terms = dbServices.getAllActiveTerms();
//
//            String selected = req.getParameter("termSelector");
//            if (selected == null) {
//                selectedTerm = dbServices.getLastActiveTerm();
//            } else {
//                selectedTerm = dbServices.getTermById(selected);
//            }
//
//            marks = dbServices.getMarksByStudentAndTermId(studentId, String.valueOf(selectedTerm.getId()));
//
//            req.setAttribute("terms", terms);
//            req.setAttribute("selectedTerm", selectedTerm);
//            req.setAttribute("marks", marks);
//
//            req.setAttribute("student", student);

//        } catch (SQLException ex) {

//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
