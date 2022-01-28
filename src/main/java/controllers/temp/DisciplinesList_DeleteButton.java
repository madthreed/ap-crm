package controllers.temp;

import database.DBServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DisciplinesList_DeleteButton", urlPatterns = "/discipline-delete")
public class DisciplinesList_DeleteButton extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String[] selectedElements = req.getParameterValues("selected");
        if (selectedElements == null){
            req.getRequestDispatcher("./WEB-INF/JSP/error.jsp").forward(req, resp);
        }

        assert selectedElements != null;
        for (String selectedElement : selectedElements) {
            DBServices dbServices = new DBServices();
            try {
                dbServices.deleteDiscipline(selectedElement);
            } catch (SQLException e) {
                req.getRequestDispatcher("./WEB-INF/JSP/sqlerror.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect("/disciplines-list");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}