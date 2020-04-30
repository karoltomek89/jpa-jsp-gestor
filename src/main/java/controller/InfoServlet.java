package controller;

import model.user.User;
import model.user.UserDAO;
import model.user.UserDAOImpl;

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
