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
import java.util.List;

@WebServlet(name = "TermController", urlPatterns = "/terms")
public class TermController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();

        try {
            List<Term> terms = dbServices.getAllActiveTerms();
            req.setAttribute("terms", terms);


            Term firstTerm = dbServices.getFirstActiveTerm();
            List<Discipline> disciplines = dbServices.getDisciplinesByTerm(String.valueOf(firstTerm.getId()));
            req.setAttribute("disciplines", disciplines);


            req.setAttribute("currentPage", "terms.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }
}
