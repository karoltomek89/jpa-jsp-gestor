package com.kt.jspjpa.gestor.controller.grades;

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

@WebServlet(name = "GetGroupListForGradesServlet", value = "/getgrouplistforgrades")
public class GetGroupListForGradesServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(EntityManagerFactoryStaticBlockSingleton.class);
    List<Group> groupList = new ArrayList<>();
    GroupDAO group = new GroupDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Object> userIdValue = Optional.ofNullable(req.getSession().getAttribute("userId").toString());

        if (userIdValue.isPresent()) {
            groupList = group.findAll();
            req.setAttribute("groupList", groupList);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/teacher/bar/teacherBarChooseGroupForGrades.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            logger.info("No userId, list not created");
        }
    }
}
