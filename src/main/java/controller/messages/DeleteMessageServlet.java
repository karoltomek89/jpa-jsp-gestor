package controller.messages;

import model.message.MessageDAO;
import model.message.MessageDAOImpl;

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
            String id = messageId.get().toString();
            message.delete(id);

            resp.sendRedirect(req.getContextPath() + "/messages");
        } else {

            resp.sendRedirect(req.getContextPath() + "/messages");
        }
    }
}

