package am.azaryan.servlet;

import am.azaryan.model.Student;
import am.azaryan.model.User;
import am.azaryan.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Student> students = studentService.getStudentByUserId(user.getId());
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req, resp);
    }
}
