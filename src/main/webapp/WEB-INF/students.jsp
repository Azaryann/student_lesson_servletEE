<%@ page import="java.util.List" %>
<%@ page import="am.azaryan.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>

<a href="/addStudent">Add Student</a>
<table border="1">
    <tr>
        <th>
            ID
        </th>
        <th>
            Name
        </th>
        <th>
            Surname
        </th>
        <th>
            Email
        </th>
        <th>
            Age
        </th>
        <th>
            Lecturer Name
        </th>
        <th>
            Added by
        </th>
        <th>
            Delete
        </th>
        <th>
            Update
        </th>
    </tr>
    <%
        if (students != null && !students.isEmpty()) {
            for (Student student : students) { %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getLesson().getName() + " " + student.getLesson().getLecturerName()%>
        </td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a>
        </td>
        <td><a href="/updateStudent?id=<%=student.getId()%>">update</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
