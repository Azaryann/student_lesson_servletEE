package am.azaryan.service;

import am.azaryan.db.DBConnectionProvider;
import am.azaryan.model.Lesson;
import am.azaryan.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public List<Lesson> getLessons() {
        List<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lesson(name, duration, lecturer_name, price) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, lesson.getName());
            ps.setString(2, DateUtil.dateToSqlTimeString(lesson.getDuration()));
            ps.setString(3, lesson.getLecturerName());
            ps.setDouble(4, lesson.getPrice());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                lesson.setId(anInt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM lesson WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Lesson lesson) {
        String sql = "UPDATE lesson SET name =?, duration = ?, lecturer_name = ?, price = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, lesson.getName());
            ps.setString(2, DateUtil.dateToSqlTimeString(lesson.getDuration()));
            ps.setString(3, lesson.getLecturerName());
            ps.setDouble(4, lesson.getPrice());
            ps.setInt(5, lesson.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Lesson getByName(String name) {
        String sql = "SELECT * FROM lesson WHERE name=" + name;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Lesson getById(int lessonId) {
        String sql = "SELECT * FROM lesson WHERE id=" + lessonId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Lesson> getLessonByUserId(int id) {
        String sql = "SELECT * FROM lesson WHERE user_id = ?";
        List<Lesson> lessons = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                try {
                    lessons.add(Lesson.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                            .lecturerName(resultSet.getString("lecturer_name"))
                            .price(resultSet.getDouble("price"))
                            .user(userService.getUserById(resultSet.getInt("user_id")))
                            .build());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }
}
