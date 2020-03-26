package controller;

import model.user.User;
import model.user.UserDAO;
import model.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherInfoServlet", value = "/teacher")
public class TeacherInfoServlet extends HttpServlet {
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = user.findById(req.getSession().getAttribute("userId").toString());
        req.setAttribute("user", newUser);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacher/bar/teacherBarGetInfo.jsp");
        dispatcher.forward(req, resp);
    }
}
