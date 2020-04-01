package controller.messages;

import model.message.MessageDAO;
import model.message.MessageDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "DeleteMessageServlet", value = "/deleteMessage")
public class DeleteMessageServlet extends HttpServlet {

    MessageDAO message = new MessageDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Optional<Object> messageId = Optional.ofNullable(req.getParameter("messageId"));

        if (!messageId.isEmpty()) {
            message.remove((req.getSession().getAttribute("messageId").toString()));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/student/bar/studentBarGetMessages.jsp");
            dispatcher.forward(req, resp);
        } else {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/student/bar/studentBarGetMessages.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

