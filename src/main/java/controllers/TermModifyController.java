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
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "TermModifyController", urlPatterns = "/term-modify")
public class TermModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyTermHiddenId");

        DBServices dbServices = new DBServices();

        try {
            Term term = dbServices.getTermById(id);
            List<Discipline> selectedDisciplines = dbServices.getDisciplinesByTerm(id);
//            List<Discipline> blockedDisciplines = dbServices.getDisciplinesWithoutMarksByTerm(id);
//            List<Discipline> unblockedDisciplines = dbServices.getDisciplinesWithoutMarksByTerm(id);
            List<Discipline> allActiveDisciplines = dbServices.getAllActiveDisciplines().stream().sorted(Comparator.comparing(Discipline::getName)).toList();
            List<Discipline> blockedDisciplines = dbServices.getMarkBlockedDisciplinesByTerm(id);


            req.setAttribute("term", term);
            req.setAttribute("selectedDisciplines", selectedDisciplines);
            req.setAttribute("allDisciplines", allActiveDisciplines);
            req.setAttribute("blockedDisciplines", blockedDisciplines);

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
        String selIds;
        String unblockedIds = "";


        if (selectedIds != null) {
            selIds = Arrays.stream(selectedIds).reduce((a, s) -> s + " " + a).orElse("");
        } else {
            selIds = "";
        }

        DBServices dbServices = new DBServices();

        try {
            unblockedIds = dbServices.getTermDisciplinesWithoutMarksByTerm(id);

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        try {
            dbServices.modifyTermById(id, duration, selIds, unblockedIds); //todo
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        resp.sendRedirect("/terms");
    }
}