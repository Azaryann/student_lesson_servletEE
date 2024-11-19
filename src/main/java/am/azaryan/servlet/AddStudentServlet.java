package am.azaryan.servlet;

import am.azaryan.model.Lesson;
import am.azaryan.model.Student;
import am.azaryan.service.LessonService;
import am.azaryan.service.StudentService;
import am.azaryan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final LessonService lessonService = new LessonService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            int age = Integer.parseInt(req.getParameter("age"));
            int lessonId = Integer.parseInt(req.getParameter("lesson_id"));
            int userId = Integer.parseInt(req.getParameter("userId"));

            String msg;
            Student byEmail = studentService.getByEmail(email);
            if (byEmail != null && byEmail.getEmail().equalsIgnoreCase(email)) {
                msg = "Student with provided email already exist";
                req.setAttribute("studentExist", msg);
                req.setAttribute("lessons", lessonService.getLessons());
                req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
            } else {
                studentService.add(Student.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonService.getById(lessonId))
                        .user(userService.getUserById(userId))
                        .build());
                resp.sendRedirect("/students");
            }
        }
}

