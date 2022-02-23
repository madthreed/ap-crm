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
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "TermController", urlPatterns = "/terms")
public class TermController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discipline> disciplines = null;
        Term selectedTerm = null;
        DBServices dbServices = new DBServices();

        String selected = req.getParameter("termSelector");

        try {
            if (selected != null) {
                selectedTerm = dbServices.getTermById(selected);
                disciplines = dbServices.getDisciplinesByTerm(selected);
            } else {
                selectedTerm = dbServices.getLastActiveTerm();
                disciplines = selectedTerm!=null?dbServices.getDisciplinesByTerm(String.valueOf(selectedTerm.getId())):null;
            }


            List<Term> terms = dbServices.getAllActiveTerms().stream().sorted(Comparator.comparing(Term::getName)).toList();

            req.setAttribute("terms", terms);
            req.setAttribute("selectedTerm", selectedTerm);
            req.setAttribute("disciplines", disciplines);

            req.setAttribute("currentPage", "terms.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("deleteTermHiddenId");

        DBServices dbServices = new DBServices();

        try {
            dbServices.deleteTermById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        resp.sendRedirect("/terms");
    }
}