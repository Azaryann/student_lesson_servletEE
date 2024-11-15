<%@ page import="am.azaryan.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<%Student student = (Student) request.getAttribute("student"); %>
Update Student<br><br>
<form method="post" action="/updateStudent">
    <input type="hidden" name="studentId" value="<%=student.getId()%>">
    Student name: <input type="text" name="name" value="<%=student.getName()%>"><br>
    Student surname: <input type="text" value="<%=student.getSurname()%>" name="surname"><br>
    Student email: <input type="text" name="email" value="<%=student.getEmail()%>"><br>
    Student age: <input type="number" name="age" value="<%=student.getAge()%>"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>
