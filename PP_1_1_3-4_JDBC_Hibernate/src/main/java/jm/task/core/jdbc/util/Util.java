package jm.task.core.jdbc.util;

import java.sql.*;

// The reason I'm using Mariadb is that the default implementation of MySQL on my Linux distro (Arch Linux) is Mariadb, and there's no actual way to change it to MySQL as far as I can see.
public class Util {

    private Util() {}

    private static Connection conn;

    // Singleton initialization of class Util
    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mariadb://localhost:3306/mydb";
                String user = "root";
                String password = "suwako";
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}