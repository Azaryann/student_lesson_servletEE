package am.azaryan.servlet;

import am.azaryan.model.Lesson;
import am.azaryan.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/lessons")
public class LessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req, resp);
    }
}
