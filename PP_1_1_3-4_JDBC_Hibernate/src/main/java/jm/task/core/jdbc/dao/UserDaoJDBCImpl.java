package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private final Connection connection = Util.getConnection();
    public void createUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Users(" +
                            "id INT NOT NULL AUTO_INCREMENT," +
                            "name VARCHAR(45) NOT NULL," +
                            "lastName VARCHAR (45) NOT NULL," +
                            "age INT NOT NULL," +
                            "PRIMARY KEY (id))");
            connection.commit();
            System.out.println("The table Users was created");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS Users");
            connection.commit();
            System.out.println("The table Users was deleted");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO Users (name, lastName, age) " +
                             "VALUES (?, ?, ?)")) {
            ps.setNString(1, name);
            ps.setNString(2, lastName);
            ps.setInt(3, age);
            ps.executeQuery();
            connection.commit();
            System.out.printf("Saved user %s, %s, %d\n", name, lastName, age);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                     "DELETE FROM Users WHERE id = (?)")) {
            ps.setLong(1, id);
            ps.executeQuery();
            connection.commit();
            System.out.println("User with id " + id + " was removed");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try (Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM Users")) {
            while (rs.next()) {
                User myUser = new User();
                {
                    myUser.setId(rs.getLong("id"));
                    myUser.setName(rs.getString("name"));
                    myUser.setLastName(rs.getString("lastName"));
                    myUser.setAge(rs.getByte("age"));
                }
                userList.add(myUser);
            }
        } catch (SQLException e) {
            System.out.println("Getting users from table error");
            e.printStackTrace();
        }
        System.out.println("Get list of users from table");
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()) {
             st.executeUpdate("TRUNCATE Users");
             connection.commit();
            System.out.println("Table Users was cleared");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        }
    }
}

