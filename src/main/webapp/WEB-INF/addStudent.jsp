<%@ page import="java.util.List" %>
<%@ page import="am.azaryan.model.Lesson" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
Add Lesson<br>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

<form method="post" action="/addStudent" enctype="multipart/form-data">
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
    <input type="submit" value="Add">
</form>
</body>
</html>
