package controller;

import model.teacher.Teacher;
import model.teacher.TeacherDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherInfoServlet", value = "/teacher")
public class TeacherGradeServlet extends HttpServlet {
    TeacherDAOImpl teacher = new TeacherDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher newTeacher = teacher.find(req.getSession().getAttribute("userId").toString());
        req.setAttribute("user", newTeacher);
//        System.out.println(newTeacher);
//  resp.getWriter().println("qwertyqwertyqwerty");
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherInfo.jsp");
       dispatcher.forward(req, resp);
    }
}
