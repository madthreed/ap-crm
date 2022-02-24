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

        List<Discipline> disciplinesByTerm = null;
        List<Mark> marksByStudentAndTermId = null;

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

        Map<Discipline, Mark> markMap = new HashMap<>();

        for (Mark mark : marksByStudentAndTermId) {
            markMap.put(mark.getDiscipline(), mark);
        }

        for (Discipline discipline : disciplinesByTerm) {
            markMap.putIfAbsent(discipline, new Mark(student, term, discipline, 0));
        }

        List<DisciplineWithMark> disciplinesWithMarks = new ArrayList<>();

        disciplinesWithMarks = markMap.entrySet().stream()
//                .peek(disciplineMarkEntry -> System.out.println(disciplineMarkEntry.getKey() + " : "+disciplineMarkEntry.getValue()))
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
                if (markIds[i].equals("0")) {
                    Discipline discipline = dbServices.getDisciplineById(String.valueOf(disciplineIds[i]));
                    dbServices.createMark(student, term, discipline, Integer.parseInt(marks[i]));
                } else {
                    Discipline discipline = dbServices.getDisciplineById(String.valueOf(disciplineIds[i]));
                    dbServices.updateMark(new Mark(Integer.parseInt(markIds[i]),student,term,discipline,Integer.parseInt(marks[i])));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        resp.sendRedirect("/students");
    }
}
