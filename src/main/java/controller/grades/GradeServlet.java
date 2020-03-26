package controller.grades;

import model.grade.GradeDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "GradeServlet", value = "/addgrade")
public class GradeServlet extends HttpServlet {
    GradeDAOImpl grade = new GradeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> gradesValue = Optional.ofNullable(req.getParameter("grade"));
        Optional<Object> usersUserId = Optional.ofNullable(req.getParameter("userId"));
        Optional<Object> subjectsSubjectId = Optional.ofNullable(req.getParameter("subjectId"));

        if (!gradesValue.isEmpty() && !usersUserId.isEmpty() && !subjectsSubjectId.isEmpty()) {
            int value = Integer.parseInt(gradesValue.get().toString());
            int userId = Integer.parseInt(usersUserId.get().toString());
            int subjectId = Integer.parseInt(subjectsSubjectId.get().toString());

            grade.save(value, userId, subjectId);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);

        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
