package filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MadThreeD on 2022.
 */

public class LoginFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoginFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // /login и нет атрибута role
        // .css .js .ttf
        // в сессии атрибут role

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();

        if (url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".ttf")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Object role = request.getSession().getAttribute("role");
        if (role == null && url.endsWith("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (role != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        response.sendRedirect("/login");
    }

    @Override
    public void destroy() {
    }
}
