package controllers;

import database.DBServices;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DisciplineModifyController", urlPatterns = "/discipline-modify")
public class DisciplineModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyDisciplineHiddenId");

        DBServices dbServices = new DBServices();
        Discipline discipline = null;

        try {
            discipline = dbServices.getDisciplineById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        req.setAttribute("discipline", discipline);
        req.setAttribute("currentPage", "discipline-modify.jsp");
        req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        DBServices dbServices = new DBServices();

        try {
            dbServices.modifyDisciplineById(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }

        resp.sendRedirect("/disciplines");
    }
}