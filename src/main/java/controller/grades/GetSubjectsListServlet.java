package controller.grades;

import model.subject.Subject;
import model.subject.SubjectDAO;
import model.subject.SubjectDAOImpl;
import model.user.User;
import model.user.UserDAO;
import model.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GetSubjectListServlet", value = "/getsubjectlist")
public class GetSubjectsListServlet extends HttpServlet {
    List<Subject> subjectList = new ArrayList<>();
    List<User> studentList = new ArrayList<>();
    SubjectDAO subject = new SubjectDAOImpl();
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> userIdValue = Optional.ofNullable(req.getSession().getAttribute("userId").toString());
        Optional<Object> groupIdValue = Optional.ofNullable(req.getParameter("groupId"));

        if (!userIdValue.isEmpty()) {
            String userId = userIdValue.get().toString();
            subjectList = subject.findAllByUserId(userId);
            req.setAttribute("subjectList", subjectList);

            String groupId = groupIdValue.get().toString();
            studentList = user.findAllByGroup(groupId);
            req.setAttribute("studentList", studentList);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacher/bar/teacherBarAddGrade.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
