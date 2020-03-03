package controller;

import model.message.Message;
import model.message.MessageDAOImpl;
import model.student.StudentDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MessageSendReadServlet", value = "/messages")
public class MessageSendReadServlet extends HttpServlet {

    List<Message> messagesList = new ArrayList<>();
    MessageDAOImpl messages = new MessageDAOImpl();
    StudentDAOImpl student = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = student.getEmail(req.getSession().getAttribute("userId").toString());
        messagesList = messages.findAllOfUser(email);
        req.setAttribute("messagesList", messagesList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/messages.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
