package am.azaryan.filter;

import am.azaryan.model.User;
import am.azaryan.model.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/home", "/addLesson", "/addStudent",
        "/deleteStudent", "/deleteLesson", "/login", "/logout","/updateLesson",
        "/updateLesson"
}
)
public class AdminAuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getUserType() == UserType.ADMIN) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
