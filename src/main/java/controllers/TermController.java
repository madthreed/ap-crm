package controllers;

import database.DBServices;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TermController", urlPatterns = "/terms")
public class TermController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DBServices dbServices = new DBServices();
            ArrayList<Term> terms = (ArrayList<Term>) dbServices.getAllActiveTerms();
            req.setAttribute("terms", terms);
            req.getRequestDispatcher("./WEB-INF/JSP/terms.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("./WEB-INF/JSP/sqlerror.jsp").forward(req, resp);
        }
    }
}
