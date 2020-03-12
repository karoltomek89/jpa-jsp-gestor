package controller;

import model.grade.Grade;
import model.grade.GradeDAO;
import model.grade.GradeDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "StudentGradeServlet", value = "/showgrades")
public class StudentGradeServlet extends HttpServlet {
    List<Grade> gradeList = new LinkedList<>();
    GradeDAO grades = new GradeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gradeList = grades.findAllByStudentId(Integer.valueOf(req.getSession().getAttribute("userId").toString()));
        req.setAttribute("grades", gradeList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studentBarGetGrades.jsp");
       dispatcher.forward(req, resp);
    }
}
