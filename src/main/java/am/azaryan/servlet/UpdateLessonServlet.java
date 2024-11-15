package am.azaryan.servlet;

import am.azaryan.model.Lesson;
import am.azaryan.service.LessonService;
import am.azaryan.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet(urlPatterns = "/updateLesson")
public class UpdateLessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Lesson lesson = lessonService.getById(id);
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("/WEB-INF/updateLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Date date = DateUtil.webTimeStringToDate(req.getParameter("duration"));
            String lecturerName = req.getParameter("lecturer_name");
            double price = Double.parseDouble(req.getParameter("price"));

            lessonService.update(Lesson.builder()
                    .id(id)
                    .name(name)
                    .duration(date)
                    .lecturerName(lecturerName)
                    .price(price)
                    .build());
            resp.sendRedirect("/index.jsp");
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
