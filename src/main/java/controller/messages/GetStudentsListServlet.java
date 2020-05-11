package controller.messages;

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

@WebServlet(name = "GetStudentListServlet", value = "/getstudentlist")
public class GetStudentsListServlet extends HttpServlet {
    List<User> userList = new ArrayList<>();
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        userList = user.findAllByMembershipType(MembershipType.STUDENT);
//        req.setAttribute("studentsList", userList);
//        RequestDispatcher dispatcher = getServletContext()
//                .getRequestDispatcher("/teacher/bar/teacherBarSendMessage.jsp");
//        dispatcher.forward(req, resp);

        Optional<Object> userIdValue = Optional.ofNullable(req.getSession().getAttribute("userId").toString());
        Optional<Object> groupIdValue = Optional.ofNullable(req.getParameter("groupId"));

        if (userIdValue.isPresent() && groupIdValue.isPresent()) {

            String groupId = groupIdValue.get().toString();
            userList = user.findAllByGroup(groupId);
            req.setAttribute("studentsList", userList);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacher/bar/teacherBarSendMessage.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            //logger.info("No userId, subject and student lists not created");
        }


    }

}
