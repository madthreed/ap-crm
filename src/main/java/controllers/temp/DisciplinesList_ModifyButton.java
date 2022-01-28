package controllers.temp;

import database.DBServices;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DisciplinesList_ModifyButton", urlPatterns = "/discipline-modifying")
public class DisciplinesList_ModifyButton extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selectedElements = req.getParameterValues("selected");
        if (selectedElements == null) {
            req.getRequestDispatcher("./WEB-INF/JSP/error.jsp").forward(req, resp);
        }

        DBServices dbServices = new DBServices();
        try {
            Discipline discipline = dbServices.getDisciplineById(selectedElements[0]);
            req.setAttribute("disciplineId",discipline.getId());
            req.setAttribute("disciplineName",discipline.getName());
            req.getRequestDispatcher("./WEB-INF/JSP/discipline-modify.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.getRequestDispatcher("./WEB-INF/JSP/sqlerror.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}