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

/*   public static Connection getConnection() {
            final String URL = "jdbc:mysql://localhost:3306/MySQL?autoReconnect=true&useSSL=false&serverTimezone=UTC";
           final String USERNAME = "root";
           final String PASSWORD = "root";
           final String DRIVER = "com.mysql.cj.jdbc.Driver";
            Connection connection = null;
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Не удалось загрузить класс драйвера!");
            }
            return connection;


        }


       /* реализуйте настройку соеденения с БД
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost:3306/MySQL?useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    private Statement statement;

// PreparedStatement preparedStatement;
   public Util() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
            statement = connection.createStatement();
            if (!connection.isClosed()) {
                System.out.println("Соединение установленно");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("driver not found");
        } catch (SQLException e) {
            System.out.println("SQL problem with connecting +\n");
            e.printStackTrace();

        }

    }
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return this.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("SQL problem with preparedStatement");
            e.printStackTrace();
        }
        return null;
    }
    public Connection getConnection() {
        return connection;
    }
// public Statement getStatement() {
// return statement;
// }
    public void connectionCloser() {
        try {
            connection.close();
// System.out.println("Соединение успешно закрыто");
        } catch (SQLException e) {
            System.out.println("SQL problem with close");
            e.printStackTrace();
        }
    }

        */


