<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
</head>
<body>
<div>
  <div>
    <h2>Register</h2>
    <form action="/register" method="post">
      <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div>
        <label for="surname">Surname:</label>
        <input type="text" id="surname" name="surname" required>
      </div>
      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
      </div>
      <select name="user_type">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
      </select>
      <button type="submit">Register</button>
    </form>
  </div>
</div>
</body>
</html>
