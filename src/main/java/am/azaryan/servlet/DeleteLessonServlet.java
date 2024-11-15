package am.azaryan.servlet;

import am.azaryan.service.LessonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteLesson")
public class DeleteLessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            lessonService.delete(id);
            resp.sendRedirect("/lessons");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
