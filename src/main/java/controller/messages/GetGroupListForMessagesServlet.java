package controller.messages;

import model.group.Group;
import model.group.GroupDAO;
import model.group.GroupDAOImpl;

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

@WebServlet(name = "GetGroupListForMessagesServlet", value = "/getgrouplistformessages")
public class GetGroupListForMessagesServlet extends HttpServlet {
    List<Group> groupList = new ArrayList<>();
    GroupDAO group = new GroupDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> userId_value = Optional.ofNullable(req.getSession().getAttribute("userId").toString());

        if (!userId_value.isEmpty()) {
            String userId = userId_value.get().toString();
            groupList = group.findAll();
            req.setAttribute("groupList", groupList);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/teacher/bar/teacherBarChooseGroupForMessages.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
