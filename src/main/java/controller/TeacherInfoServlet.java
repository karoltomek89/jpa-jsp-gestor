package controller;

import model.user.User;
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
    UserDAOImpl teacher = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = teacher.find(req.getSession().getAttribute("userId").toString());
        req.setAttribute("user", newUser);
//        System.out.println(newTeacher);
//  resp.getWriter().println("qwertyqwertyqwerty");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherBarGetInfo.jsp");
        dispatcher.forward(req, resp);
    }
}
