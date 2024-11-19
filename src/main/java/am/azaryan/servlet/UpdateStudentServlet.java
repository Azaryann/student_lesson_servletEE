package am.azaryan.servlet;

import am.azaryan.model.Lesson;
import am.azaryan.model.Student;
import am.azaryan.service.LessonService;
import am.azaryan.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final LessonService lessonService = new LessonService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getLessons();
        req.setAttribute("lessons", lessons);
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            int age = Integer.parseInt(req.getParameter("age"));
            studentService.update(Student.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .build());
            resp.sendRedirect("/students");
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
