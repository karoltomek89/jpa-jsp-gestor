package controller;

import model.SessionFactory;
import model.student.Student;
import model.student.StudentDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentInfoServlet", value = "/student")
public class StudentInfoServlet extends HttpServlet {
    StudentDAOImpl student = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student newStudent = student.find(req.getSession().getAttribute("studentId").toString());
        req.setAttribute("user", newStudent);
//        System.out.println(newStudent);
//  resp.getWriter().println("qwertyqwertyqwerty");
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studentInfo.jsp");
       dispatcher.forward(req, resp);
    }
}
