<%@ page import="am.azaryan.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Lesson</title>
</head>
<body>
<h1><a href="/index.jsp">Main Page</a></h1>
<h1><a href="/lessons">Lessons Page</a></h1>
<%Lesson lesson = (Lesson) request.getAttribute("lesson"); %>
Update Lesson<br><br>
<form method="post" action="/updateLesson">
    <input type="hidden" name="id" value="<%=lesson.getId()%>">
    Lesson name: <input type="text" name="name" value="<%=lesson.getName()%>"><br>
    Lesson duration: <input type="number" step="1" min="1" max="120" value="<%=lesson.getDuration()%>"
                            name="duration"><br>
    Lesson lecturer name: <input type="text" name="lecturer_name" value="<%=lesson.getLecturerName()%>"><br>
    Lesson price: <input type="number" name="price" value="<%=lesson.getPrice()%>"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
