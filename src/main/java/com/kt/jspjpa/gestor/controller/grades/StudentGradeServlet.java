package com.kt.jspjpa.gestor.controller.grades;

import com.kt.jspjpa.gestor.model.grade.GradeDAO;
import com.kt.jspjpa.gestor.model.grade.GradeDAOImpl;
import com.kt.jspjpa.gestor.model.grade.GradeWithSubjectName;

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
    List<GradeWithSubjectName> gradeList = new LinkedList<>();
    GradeDAO grades = new GradeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gradeList = grades
                .findAllByStudentIdWithName(Integer.valueOf(req.getSession().getAttribute("userId").toString()));
        req.setAttribute("grades", gradeList);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/student/bar/studentBarGetGrades.jsp");
        dispatcher.forward(req, resp);
    }
}
