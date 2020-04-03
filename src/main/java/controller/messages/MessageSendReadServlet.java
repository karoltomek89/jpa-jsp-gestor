package controller.messages;

import model.message.Message;
import model.message.MessageDAO;
import model.message.MessageDAOImpl;
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

@WebServlet(name = "MessageSendReadServlet", value = "/messages")
public class MessageSendReadServlet extends HttpServlet {

    List<Message> messagesList = new ArrayList<>();
    MessageDAO message = new MessageDAOImpl();
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = user.getEmail(req.getSession().getAttribute("userId").toString());
        messagesList = message.findAllOfUser(email);

        req.setAttribute("messagesList", messagesList);

        RequestDispatcher dispatcher;

        switch (user.getMembershipById(
                user.getMembershipId(
                        Integer.parseInt(req.getSession().getAttribute("userId").toString())))) {
            case STUDENT:
                dispatcher = getServletContext().getRequestDispatcher("/student/bar/studentBarGetMessages.jsp");
                break;
            case PARENT:
                dispatcher = getServletContext().getRequestDispatcher("/parent/bar/parentBarGetMessages.jsp");
                break;
            default:
                dispatcher = getServletContext().getRequestDispatcher("index.jsp");
        }
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String teacherEmail = user.getEmail(req.getSession().getAttribute("userId").toString());
        Optional<Object> toValue = Optional.ofNullable(req.getParameter("studentId"));
        Optional<Object> topicValue = Optional.ofNullable(req.getParameter("topic"));
        Optional<Object> textValue = Optional.ofNullable(req.getParameter("text"));

        if (!toValue.isEmpty() && !teacherEmail.isEmpty() && !topicValue.isEmpty() && !textValue.isEmpty()) {
            String from = teacherEmail;
            String to = user.getEmail(toValue.get().toString());
            String topic = topicValue.get().toString();
            String text = textValue.get().toString();

            message.insert(from, to, topic, text);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacher/bar/teacherBarMessageSended.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacher/bar/teacherBarMessageNotSended.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

