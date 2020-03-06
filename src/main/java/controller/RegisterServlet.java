package controller;

import model.student.StudentDAOImpl;
import model.teacher.TeacherDAOImpl;

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
    TeacherDAOImpl teacher = new TeacherDAOImpl();
    StudentDAOImpl student = new StudentDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> optional = Optional.ofNullable(req.getParameter("type"));

        if (!optional.isEmpty()) {
            String accesType = optional.get().toString();
            if (accesType.equals("student")) {
                student.register(
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        1);
            }else if (accesType.equals("teacher")) {
                teacher.register(
                        req.getParameter("name"),
                        req.getParameter("surname"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        3);
            }
            else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
