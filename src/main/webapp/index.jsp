<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <div>
        <h2>Login</h2>
        <% if (session.getAttribute("msg") != null) {%>
        <span> <%=session.getAttribute("msg")%></span>
        <%session.removeAttribute("msg");%>
        <%}%>
        <form action="login" method="post">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="/register"><button class="register-btn">Register</button></a></p>
    </div>
</div>

</body>
</html>