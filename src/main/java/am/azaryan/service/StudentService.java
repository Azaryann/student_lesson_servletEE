package am.azaryan.service;

import am.azaryan.db.DBConnectionProvider;
import am.azaryan.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private LessonService lessonService = new LessonService();
    private UserService userService = new UserService();

    public void add(Student student) {
        String sql = "INSERT INTO student(name, surname, email, age, lesson_id, user_id) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5, student.getLesson().getId());
            ps.setInt(6, student.getUser().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                student.setId(anInt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email=" + email;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonService.getById(resultSet.getInt("lesson_id")))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Student> getStudents() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonService.getById(resultSet.getInt("lesson_id")))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudentByUserId(int id) {
        String sql = "SELECT * FROM student WHERE user_id =?";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonService.getById(resultSet.getInt("lesson_id")))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getById(int studentId) {
        String sql = "SELECT * FROM student WHERE id=" + studentId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonService.getById(resultSet.getInt("lesson_id")))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, surname = ?, email = ?, age = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getAge());
            ps.setInt(5,student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
