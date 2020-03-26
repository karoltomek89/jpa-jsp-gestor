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

@WebServlet(name = "RegisterServlet", value = "/addgrade")
public class GradeServlet extends HttpServlet {
    GradeDAOImpl grade = new GradeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> grades_value = Optional.ofNullable(req.getParameter("grade"));
        Optional<Object> users_userId = Optional.ofNullable(req.getParameter("userId"));
        Optional<Object> subjects_subjectId = Optional.ofNullable(req.getParameter("subjectId"));

        if (!grades_value.isEmpty() && !users_userId.isEmpty() && !subjects_subjectId.isEmpty()) {
            int value = Integer.parseInt(grades_value.get().toString());
            int userId = Integer.parseInt(users_userId.get().toString());
            int subjectId = Integer.parseInt(subjects_subjectId.get().toString());

            grade.save(value, userId, subjectId);

        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
