package controller;

import model.SQLSessionFactory;
import model.user.UserDAO;
import model.user.UserDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    UserDAO user = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = user.login(
                req.getParameter("inputEmail"),
                req.getParameter("inputPassword"));

        HttpSession session = req.getSession();
        session.setAttribute("userId", userId);
        session.setAttribute("membershipId", user.getMembershipId(userId));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);


        // ustawienie ciasteczka z ID użytkownika
//        if (id != 0) {
//            Cookie cookie = new Cookie("LoginCookie", String.valueOf(100));
//            cookie.setMaxAge(100);
//            cookie.setValue(Integer.toString(id));
//            resp.addCookie(cookie);
//            logger.info("studet founded");
//        } else {
//            logger.info("user not found");
//        }


    }
}
