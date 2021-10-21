package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
// реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_ADMIN = "root";
    private static final String PASSWORD = "root";
    private static  final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public Util() {
    }
    public static String getUrl() {
        return URL;
    }
    public static String getUser() {
        return DB_ADMIN;
    }
    public static String getPassword() {
        return PASSWORD;
    }
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());

// if (connection != null) {
// System.out.println("Connection ok");
// }
        } catch (SQLException sqlEx) {
            System.out.println("Connection failure! Please check username, password and url string");
        }
        return connection;
    }
}


