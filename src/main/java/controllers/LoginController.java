package controllers;

import database.DBServices;
import entity.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MadThreeD on 2022.
 */

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices dbServices = new DBServices();
        try {
            List<Role> roles = dbServices.getAllRoles();
            req.setAttribute("roles", roles);

            req.setAttribute("currentPage", "login.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("currentPage", "sqlerror.jsp");
            req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        DBServices dbServices = new DBServices();
        try {
            if (dbServices.authUser(login, password, role)) {
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("login", login);
                resp.sendRedirect("/home");
            } else {
                req.setAttribute("message", "1");
                List<Role> roles = dbServices.getAllRoles();
                req.setAttribute("roles", roles);

                req.setAttribute("currentPage", "login.jsp");
                req.getRequestDispatcher("./WEB-INF/JSP/template.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
