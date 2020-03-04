package controller;

import model.student.Student;
import model.student.StudentDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "GetStudentListServlet", value = "/getstudentlist")
public class GetStudentsListServlet extends HttpServlet {
    List<Student> studentsList = new LinkedList<>();
    StudentDAOImpl student = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        studentsList = student.findAll();
        req.setAttribute("studentsList", studentsList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherBarSendMessage.jsp");
        dispatcher.forward(req, resp);

    }
}
