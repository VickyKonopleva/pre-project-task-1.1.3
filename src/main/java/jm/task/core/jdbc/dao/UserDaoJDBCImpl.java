package jm.task.core.jdbc.dao;
import java.sql.*;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private long USERS_COUNT = 1;

    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE USERS("
                    + "id LONG,"
                    + " name VARCHAR(20),"
                    + " lastname VARCHAR(20), "
                    + "age SMALLINT)";
            statement.executeUpdate(SQL);
        } catch (SQLException|RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE USERS";
            statement.executeUpdate(SQL);
        } catch (SQLException|RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, USERS_COUNT++);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setByte(4, user.getAge());
            preparedStatement.executeUpdate();

        } catch (SQLException|RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM USERS WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException|RuntimeException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM USERS";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getByte("age"));
                user.setLastName(resultSet.getString("lastname"));
                users.add(user);
            }
        } catch (SQLException|RuntimeException e) {
            e.printStackTrace();
        }
    return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "TRUNCATE USERS";
            statement.executeUpdate(SQL);
        } catch (SQLException|RuntimeException e) {
            e.printStackTrace();
        }
    }
}
