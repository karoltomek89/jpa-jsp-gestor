package com.kt.jspjpa.gestor.controller.messages;

import com.kt.jspjpa.gestor.model.databases.EntityManagerFactoryStaticBlockSingleton;
import com.kt.jspjpa.gestor.model.user.User;
import com.kt.jspjpa.gestor.model.user.UserDAO;
import com.kt.jspjpa.gestor.model.user.UserDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(EntityManagerFactoryStaticBlockSingleton.class);
    List<User> userList = new ArrayList<>();
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            logger.info("No userId, subject and student lists not created");
        }
    }
}
