package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(servletNames = {"StudentInfoServlet", "TeacherInfoServlet"})
public class UserFilters implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Optional<Object> optional = Optional.ofNullable(req.getSession().getAttribute("acces_accesId"));

        if (!optional.isEmpty()) {
            String accesType = (String) optional.get().toString();
            if (accesType.equals("1")) {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/student");
                dispatcher.forward(req, resp);
            }else if (accesType.equals("3")) {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/teacher");
                dispatcher.forward(req, resp);
            }
            else {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
