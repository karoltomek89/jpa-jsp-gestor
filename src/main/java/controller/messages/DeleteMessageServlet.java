package controller.messages;

import model.SQLSessionFactory;
import model.message.MessageDAO;
import model.message.MessageDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "DeleteMessageServlet", value = "/deleteMessage")
public class DeleteMessageServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);
    MessageDAO message = new MessageDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Optional<Object> messageId = Optional.ofNullable(req.getParameter("messageId"));

        if (messageId.isPresent()) {
            String id = messageId.get().toString();
            message.delete(id);
            resp.sendRedirect(req.getContextPath() + "/messages");
        } else {
            resp.sendRedirect(req.getContextPath() + "/messages");
            logger.info("No messageId, message not deleted");
        }
    }
}

