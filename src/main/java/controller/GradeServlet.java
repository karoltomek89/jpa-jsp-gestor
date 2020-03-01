package controller;

import model.grade.GradeDAOImpl;
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

@WebServlet(name = "RegisterServlet", value = "/addgrade")
public class GradeServlet extends HttpServlet {
    GradeDAOImpl grade = new GradeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> grades_value = Optional.ofNullable(req.getParameter("value"));
        Optional<Object> students_studentId = Optional.ofNullable(req.getParameter("studentId"));
        Optional<Object> subjects_subjectId = Optional.ofNullable(req.getParameter("subjectId"));

        if (!grades_value.isEmpty() && !students_studentId.isEmpty() && !subjects_subjectId.isEmpty()) {
            int value = Integer.parseInt( grades_value.get().toString());
            int studentId = Integer.parseInt( students_studentId.get().toString());
            int subjectId = Integer.parseInt( subjects_subjectId.get().toString());

            grade.save(value, studentId, subjectId);

        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherInfo.jsp");
            dispatcher.forward(req, resp);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherInfo.jsp");
        dispatcher.forward(req, resp);
    }
}
