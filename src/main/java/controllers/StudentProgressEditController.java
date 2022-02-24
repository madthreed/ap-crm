package controllers;

import database.DBServices;
import dto.DisciplineWithMark;
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

        List<Discipline> disciplinesByTerm = null;
        List<Mark> marksByStudentAndTermId = null;

        List<DisciplineWithMark> disciplinesWithMarks = new ArrayList<>();

        String studentId = req.getParameter("studentId");
        Student student = null;

        String termId = req.getParameter("termId");
        Term term = null;

        try {
            student = dbServices.getStudentById(studentId);
            req.setAttribute("student", student);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        try {
            term = dbServices.getTermById(termId);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }


        try {
            disciplinesByTerm = dbServices.getDisciplinesByTerm(termId);
            req.setAttribute("disciplines", disciplinesByTerm);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }


        try {
            marksByStudentAndTermId = dbServices.getMarksByStudentAndTermId(studentId, termId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Discipline discipline : disciplinesByTerm) {
            for (Mark mark : marksByStudentAndTermId) {
                if (discipline.getId() == mark.getDiscipline().getId()) {
                    disciplinesWithMarks.add(new DisciplineWithMark(discipline, mark));
                } else {
                    Mark newMark = new Mark(student,term,discipline,0);
                    //                        dbServices.createMark(student, term, discipline, 0);
                    disciplinesWithMarks.add(new DisciplineWithMark(discipline, newMark));
                }
            }
        }

        req.setAttribute("disciplinesWithMarks", disciplinesWithMarks);
        req.setAttribute("currentPage", "student-progress-edit.jsp");
        req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] marks = req.getParameterValues("marks");
        String[] disciplineIds = req.getParameterValues("disciplineId");
        String[] markIds = req.getParameterValues("markId");

        resp.sendRedirect("/students");
    }
}
