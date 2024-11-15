<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>
Add Lesson<br>
<form method="post" action="/addLesson" enctype="multipart/form-data">
    Lessons name: <input type="text" name="name"><br>
    Lessons duration: <input type="number" step="1" min="1" max="120" value="40" name="duration"><br>
    Lessons lecturer name: <input type="text" name="lecturer_name"><br>
    Lessons price: <input type="number" name="price"><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
