package controller;

import model.SessionFactory;
import model.student.StudentDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);

    StudentDAOImpl student = new StudentDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = student.login(
                req.getParameter("inputEmail"),
                req.getParameter("inputPassword"));
        int access = 1;

        // ustawienie ciasteczka z ID użytkownika
//        if (id != 0) {
//            Cookie cookie = new Cookie("LoginCookie", String.valueOf(100));
//            cookie.setMaxAge(100);
//            cookie.setValue(Integer.toString(id));
//            resp.addCookie(cookie);
//            logger.info("studet founded");
//        } else {
//            logger.info("student not found");
//        }

        if(id > 0) {
            HttpSession session = req.getSession();
            session.setAttribute("studentId", id);
            session.setAttribute("acces_accesId", access);
        }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);

    }
}
