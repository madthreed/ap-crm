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
import java.util.stream.Collectors;

@WebServlet(name = "StudentProgressEditController", urlPatterns = "/student-progress-edit")
public class StudentProgressEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();

        String studentId = req.getParameter("studentId");
        String termId = req.getParameter("termId");

        List<Discipline> disciplinesByTerm = null;
        List<Mark> marksByStudentAndTermId = null;
        Student student = null;
        Term term = null;

        try {
            student = dbServices.getStudentById(studentId);
            term = dbServices.getTermById(termId);
            disciplinesByTerm = dbServices.getDisciplinesByTerm(termId);
            marksByStudentAndTermId = dbServices.getMarksByStudentAndTermId(studentId, termId);

            req.setAttribute("student", student);
            req.setAttribute("term", term);
            req.setAttribute("disciplines", disciplinesByTerm);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        Map<Discipline, Mark> markMap = new HashMap<>();

        assert marksByStudentAndTermId != null;
        for (Mark mark : marksByStudentAndTermId) {
            markMap.put(mark.getDiscipline(), mark);
        }

        for (Discipline discipline : disciplinesByTerm) {
            markMap.putIfAbsent(discipline, new Mark(student, term, discipline, 0));
        }

        List<DisciplineWithMark> disciplinesWithMarks;

        disciplinesWithMarks = markMap.entrySet().stream()
                .map((k) -> new DisciplineWithMark(k.getKey(), k.getValue()))
                .collect(Collectors.toList());

        req.setAttribute("disciplinesWithMarks", disciplinesWithMarks);

        req.setAttribute("currentPage", "student-progress-edit.jsp");
        req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();

        String studentId = req.getParameter("studentId");
        String termId = req.getParameter("termId");

        String[] marks = req.getParameterValues("marks");
        String[] disciplineIds = req.getParameterValues("disciplineId");
        String[] markIds = req.getParameterValues("markId");

        try {
            Student student = dbServices.getStudentById(studentId);
            Term term = dbServices.getTermById(String.valueOf(termId));

            for (int i = 0; i < markIds.length; i++) {
                Discipline discipline = dbServices.getDisciplineById(String.valueOf(disciplineIds[i]));
                if (markIds[i].equals("0")) {
                    dbServices.createMark(student, term, discipline, Integer.parseInt(marks[i]));
                } else {
                    dbServices.updateMark(new Mark(Integer.parseInt(markIds[i]), student, term, discipline, Integer.parseInt(marks[i])));
                }
            }

//            req.setAttribute("student", student);
//            req.setAttribute("currentPage", "student-progress.jsp");
//            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);

            resp.sendRedirect("/student-progress?progressStudentId="+studentId+"");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

    }
}
