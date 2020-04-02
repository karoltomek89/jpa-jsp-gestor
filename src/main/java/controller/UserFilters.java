package controller;

import model.Membership;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(servletNames = {"StudentInfoServlet", "TeacherInfoServlet", "ParentInfoServlet"})
public class UserFilters implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Optional<Object> optional = Optional.ofNullable(req.getSession().getAttribute("membershipId"));

        if (!optional.isEmpty()) {
            int membershipId = Integer.parseInt(optional.get().toString());
            if (membershipId == Membership.STUDENT.getMembershipId()) {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/student");
                dispatcher.forward(req, resp);
            } else if (membershipId == Membership.TEACHER.getMembershipId()) {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/teacher");
                dispatcher.forward(req, resp);
            } else if (membershipId == Membership.PARENT.getMembershipId()) {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/parent");
                dispatcher.forward(req, resp);
            } else if (membershipId == Membership.DIRECTOR.getMembershipId()) {
                RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/director");
                dispatcher.forward(req, resp);
            } else {
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
