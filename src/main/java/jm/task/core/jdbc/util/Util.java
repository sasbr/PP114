package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydata";
    private static final String DB_ADMIN = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    private static SessionFactory sessionfactory = null;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, DB_ADMIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", PASSWORD)
                    .setProperty("hibernate.connection.password", DB_ADMIN)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionfactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Problems !!! " + e);
        }
        return sessionfactory;
    }

    public static void shutdown() {
        if (connection != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
               // e.printStackTrace();
                System.out.println("ex");
            }
        }
        if (sessionfactory != null) {
            getSessionFactory().close();
            System.out.println("ex1");
        }
    }
}



