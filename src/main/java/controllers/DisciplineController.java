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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MadThreeD on 2022.
 */

@WebServlet(name = "DisciplineController", urlPatterns = "/disciplines")
public class DisciplineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();

        try {
            List<Discipline> disciplines = dbServices.getAllActiveDisciplines().stream().sorted(Comparator.comparing(Discipline::getName)).collect(Collectors.toList());

            req.setAttribute("disciplines", disciplines);
            req.setAttribute("currentPage", "disciplines.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("deleteDisciplineHiddenId");
        String[] idsDelete = ids.split(" ");

        DBServices dbServices = new DBServices();

        for (String id : idsDelete) {
            try {
                dbServices.deleteDisciplineById(id);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("currentPage", "sqlerror.jsp");
                req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
            }
        }

        resp.sendRedirect("/disciplines");
    }
}
