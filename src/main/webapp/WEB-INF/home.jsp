<%@ page import="am.azaryan.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<header>
    <h1>Home Page</h1>
</header>

<div>
    <% if (session.getAttribute("user") != null) {
        User user = (User) session.getAttribute("user");
    %>
    <div>Welcome <%=user.getName() + " " + user.getSurname()%></div>
    <a href="/logout">Logout</a>
    <% } %>

    <div style="margin-top: 20px;">
        <a href="/lessons">View all lessons</a> <br>
        <a href="/students">View all students</a> <br>
    </div>
</div>
</body>
</html>
