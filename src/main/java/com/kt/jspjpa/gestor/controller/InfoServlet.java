package com.kt.jspjpa.gestor.controller;

import com.kt.jspjpa.gestor.model.membership.MembershipType;
import com.kt.jspjpa.gestor.model.user.User;
import com.kt.jspjpa.gestor.model.user.UserDAO;
import com.kt.jspjpa.gestor.model.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InfoServlet", value = "/info")
public class InfoServlet extends HttpServlet {
    UserDAO user = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = user.findById(req.getSession().getAttribute("userId").toString());
        MembershipType membershipType = user.getMembershipTypeById(user.getMembershipTypeId((Integer) req.getSession().getAttribute("userId"))); //TODO new method must be created
        String membership = membershipType.name();

        req.setAttribute("membership", membership);
        req.setAttribute("user", newUser);

        RequestDispatcher dispatcher;

        switch (user.getMembershipTypeById(
                user.getMembershipTypeId(
                        Integer.parseInt(req.getSession().getAttribute("userId").toString())))) {
            case STUDENT:
                dispatcher = getServletContext().getRequestDispatcher("/student/bar/studentBarGetInfo.jsp");
                break;
            case PARENT:
                dispatcher = getServletContext().getRequestDispatcher("/parent/bar/parentBarGetInfo.jsp");
                break;
            case TEACHER:
                dispatcher = getServletContext().getRequestDispatcher("/teacher/bar/teacherBarGetInfo.jsp");
                break;
            case DIRECTOR:
                dispatcher = getServletContext().getRequestDispatcher("/director/bar/directorBarGetInfo.jsp");
                break;
            default:
                dispatcher = getServletContext().getRequestDispatcher("index.jsp");
        }
        dispatcher.forward(req, resp);
    }
}
