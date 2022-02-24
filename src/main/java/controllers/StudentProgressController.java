package controllers;

import database.DBServices;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Term selectedTerm;

        DBServices dbServices = new DBServices();

        try {
            String studentId = req.getParameter("progressStudentId");
            Student student = dbServices.getStudentById(studentId);

            List<Term> terms = dbServices.getAllActiveTerms().stream().sorted(Comparator.comparing(Term::getName)).collect(Collectors.toList());

            String selected = req.getParameter("termSelector");
            if (selected == null) {
                selectedTerm = dbServices.getLastActiveTerm();
            } else {
                selectedTerm = dbServices.getTermById(selected);
            }

            List<Mark> marks = dbServices.getMarksByStudentAndTermId(studentId, String.valueOf(selectedTerm.getId()));


            double averageMark = marks.stream()
                    .mapToDouble(Mark::getMark)
                    .average().orElse(0.0);


            req.setAttribute("averageMark", String.format("%.2f", averageMark));
            req.setAttribute("terms", terms);
            req.setAttribute("selectedTerm", selectedTerm);
            req.setAttribute("marks", marks);

            req.setAttribute("student", student);
            req.setAttribute("currentPage", "student-progress.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        } catch (SQLException ex) {
            ex.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }
}
