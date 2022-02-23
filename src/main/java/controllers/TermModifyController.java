package controllers;

import database.DBServices;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

@WebServlet(name = "TermModifyController", urlPatterns = "/term-modify")
public class TermModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyTermHiddenId");

        DBServices dbServices = new DBServices();

        try {
            Term term = dbServices.getTermById(id);
            List<Discipline> selectedDisciplines = dbServices.getDisciplinesByTerm(id);
            List<Discipline> allDisciplines = dbServices.getAllActiveDisciplines();

            req.setAttribute("term", term);
            req.setAttribute("selectedDisciplines", selectedDisciplines);
            req.setAttribute("allDisciplines", allDisciplines);

            req.setAttribute("currentPage", "term-modify.jsp");
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
        String duration = req.getParameter("duration");
        String[] selectedIds = req.getParameterValues("disciplineSelector");
        String selIds = Arrays.stream(selectedIds).reduce((a, s) -> s + " " + a).orElse("");

        DBServices dbServices = new DBServices();

        try {
            dbServices.modifyTermById(id, duration, selIds); //todo
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        resp.sendRedirect("/terms");
    }
}