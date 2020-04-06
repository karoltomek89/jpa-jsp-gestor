package controller.grades;

import model.SQLSessionFactory;
import model.grade.GradeDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> gradesValue = Optional.ofNullable(req.getParameter("grade"));
        Optional<Object> usersUserId = Optional.ofNullable(req.getParameter("userId"));
        Optional<Object> subjectsSubjectId = Optional.ofNullable(req.getParameter("subjectId"));

        if (gradesValue.isPresent() && usersUserId.isPresent() && subjectsSubjectId.isPresent()) {
            int value = Integer.parseInt(gradesValue.get().toString());
            int userId = Integer.parseInt(usersUserId.get().toString());
            int subjectId = Integer.parseInt(subjectsSubjectId.get().toString());

            grade.save(value, userId, subjectId);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/teacher/bar/teacherBarGradeAdded.jsp");
            dispatcher.forward(req, resp);

        } else {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/teacher/bar/teacherBarGradeNotAdded.jsp");
            dispatcher.forward(req, resp);
            logger.info("No value or userId or subjectId, grade not added");
        }
    }
}
