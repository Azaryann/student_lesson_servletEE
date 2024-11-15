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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            int age = Integer.parseInt(req.getParameter("age"));
            int lessonId = Integer.parseInt(req.getParameter("lesson_id"));
            Lesson byId = lessonService.getById(lessonId);

            Student byEmail = studentService.getByEmail(email);
            if (byEmail == null) {
                studentService.add(Student.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(byId)
                        .build());
                resp.sendRedirect("/index.jsp");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

