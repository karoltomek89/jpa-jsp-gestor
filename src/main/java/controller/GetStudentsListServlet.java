package controller;

import model.Membership;
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

@WebServlet(name = "GetStudentListServlet", value = "/getstudentlist")
public class GetStudentsListServlet extends HttpServlet {
    List<User> userList = new ArrayList<>();
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userList = user.findAllByMembership(Membership.STUDENT);
        req.setAttribute("studentsList", userList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherBarSendMessage.jsp");
        dispatcher.forward(req, resp);

    }
}
