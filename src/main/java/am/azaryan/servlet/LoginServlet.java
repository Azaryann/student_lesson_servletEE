package am.azaryan.servlet;

import am.azaryan.model.User;
import am.azaryan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User userByEmail = userService.getUserByEmail(email);
        if (userByEmail != null && password.equals(userByEmail.getPassword())) {
            req.getSession().setAttribute("user", userByEmail);
            resp.sendRedirect("/home");
        } else {
            req.getSession().setAttribute("msg", "Invalid email or password");
            resp.sendRedirect("/");
        }

    }
}
