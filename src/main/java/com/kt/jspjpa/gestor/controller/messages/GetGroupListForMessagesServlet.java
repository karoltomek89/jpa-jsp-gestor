package com.kt.jspjpa.gestor.controller.messages;

import com.kt.jspjpa.gestor.model.databases.EntityManagerFactoryStaticBlockSingleton;
import com.kt.jspjpa.gestor.model.group.Group;
import com.kt.jspjpa.gestor.model.group.GroupDAO;
import com.kt.jspjpa.gestor.model.group.GroupDAOImpl;
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

@WebServlet(name = "GetGroupListForMessagesServlet", value = "/getgrouplistformessages")
public class GetGroupListForMessagesServlet extends HttpServlet {
    List<Group> groupList = new ArrayList<>();
    GroupDAO group = new GroupDAOImpl();
    private static final Logger logger = LoggerFactory.getLogger(EntityManagerFactoryStaticBlockSingleton.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> userId_value = Optional.ofNullable(req.getSession().getAttribute("userId").toString());

        if (userId_value.isPresent()) {
            groupList = group.findAll();
            req.setAttribute("groupList", groupList);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/teacher/bar/teacherBarChooseGroupForMessages.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            logger.info("No userId, groupList not created");
        }
    }
}
