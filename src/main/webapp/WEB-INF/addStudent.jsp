<%@ page import="java.util.List" %>
<%@ page import="am.azaryan.model.Lesson" %>
<%@ page import="am.azaryan.model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
Add Lesson<br>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>
<%String message = (String) request.getAttribute("studentExist");%>

<form method="post" action="/addStudent">
    <%if (message != null) {%>
    <span style="color: #302c2c"><%=message%></span> <br>
    <%}%>
    Students name: <input type="text" name="name"><br>
    Students surname: <input type="text" name="surname"><br>
    Students email: <input type="text" name="email"><br>
    Students age: <input type="number" name="age"><br>
    <select name="lesson_id">
        <%
            for (Lesson lesson : lessons) {%>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
        </option>
        <%}%>
    </select>
    <%User user = (User) request.getSession().getAttribute("user");%>
    <input type="hidden" name="user_id" value="<%=user.getId()%>">
    <input type="submit" value="Add">
</form>
</body>
</html>
