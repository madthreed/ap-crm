package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeController", urlPatterns = "/home")
public class HomeController extends HttpServlet{
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(req.getParameter("students")!=null){
//            req.getRequestDispatcher("./WEB-INF/JSP/students.jsp").forward(req,resp);
//        }
//
//        if(req.getParameter("disciplines")!=null){
//            req.getRequestDispatcher("./WEB-INF/JSP/disciplines.jsp").forward(req,resp);
//        }
//
//        if(req.getParameter("terms")!=null){
//            req.getRequestDispatcher("./WEB-INF/JSP/terms.jsp").forward(req,resp);
//        }
//
//        req.getRequestDispatcher("./WEB-INF/JSP/home.jsp").forward(req,resp);
//    }
//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./WEB-INF/JSP/home.jsp").forward(req,resp);
    }
}
