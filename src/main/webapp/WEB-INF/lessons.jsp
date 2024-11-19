<%@ page import="java.util.List" %>
<%@ page import="am.azaryan.model.Lesson" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lessons</title>
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons"); %>

<a href="/addLesson">Add Lesson</a>
<table border="1">
    <tr>
        <th>
            Name
        </th>
        <th>
            Duration
        </th>
        <th>
            Added by
        </th>
        <th>
            LecturerName
        </th>
        <th>
            Price
        </th>
        <th>
            Delete
        </th>
        <th>
            Update
        </th>
    </tr>
    <%
        if (lessons != null && !lessons.isEmpty()) {
            for (Lesson lesson : lessons) { %>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=lesson.getDuration()%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">delete</a>
        </td>
        <td><a href="/updateLesson?id=<%=lesson.getId()%>">update</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
