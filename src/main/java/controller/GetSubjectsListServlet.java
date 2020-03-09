package controller;

import model.subject.Subject;
import model.subject.SubjectDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GetSubjectListServlet", value = "/getsubjectlist")
public class GetSubjectsListServlet extends HttpServlet {
    List<Subject> subjectList = new LinkedList<>();
    SubjectDAOImpl subject = new SubjectDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> userId_value = Optional.ofNullable(req.getSession().getAttribute("userId").toString());

        if (!userId_value.isEmpty()) {
            String userId = userId_value.get().toString();
            subjectList = subject.findAllByUserId(userId);
            req.setAttribute("subjectList", subjectList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userBarAddGrade.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
