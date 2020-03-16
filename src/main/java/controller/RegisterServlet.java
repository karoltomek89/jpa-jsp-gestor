package controller;

import model.Membership;
import model.user.UserDAO;
import model.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> optional = Optional.ofNullable(req.getParameter("type"));

        if (!optional.isEmpty()) {
            String accesType = optional.get().toString();
            if (accesType.equals("student")) {
                user.register(
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        Membership.STUDENT);
            } else if (accesType.equals("teacher")) {
                user.register(
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        Membership.TEACHER);
            } else if (accesType.equals("parent")) {
                user.register(
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        Membership.PARENT);
            } else if (accesType.equals("director")) {
                user.register(
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        Membership.DIRECTOR);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
