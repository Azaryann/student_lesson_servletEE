package am.azaryan.servlet;

import am.azaryan.service.StudentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            studentService.delete(id);
            resp.sendRedirect("/students");
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
