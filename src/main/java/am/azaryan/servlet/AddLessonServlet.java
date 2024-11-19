package am.azaryan.servlet;

import am.azaryan.model.Lesson;
import am.azaryan.model.User;
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

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException, IOException, ServletException {
        String name = req.getParameter("name");
        Date date;
        try {
            date = DateUtil.webTimeStringToDate(req.getParameter("duration"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String lecturerName = req.getParameter("lecturer_name");
        double price = Double.parseDouble(req.getParameter("price"));

        Lesson byName = lessonService.getByName(name);
        String message;
        if (byName.getName() != null && byName.getName().equalsIgnoreCase(name)) {
            message = "Lesson already exist";
            req.setAttribute("lessonExist", message);
            req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            lessonService.add(Lesson.builder()
                    .name(name)
                    .duration(date)
                    .lecturerName(lecturerName)
                    .price(price)
                    .user(user)
                    .build());
            resp.sendRedirect("/lessons");
        }
    }
}
