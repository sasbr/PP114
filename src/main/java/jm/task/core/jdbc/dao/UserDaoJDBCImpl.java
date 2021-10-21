package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE users (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age TINYINT(3), PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("SQL error occured 1");
        }
    }
    @Override
    public void dropUsersTable() {
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("SQL error occured 2");
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try ( Connection conn = Util.connect();
              PreparedStatement preStmt = conn.prepareStatement("INSERT INTO users VALUES (default, ?, ?, ?)")) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setByte(3, age);
            preStmt.execute();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("SQL error occured 3");
        }
    }
    @Override
    public void removeUserById(long id) {
        try (Connection conn = Util.connect();
             PreparedStatement preStmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")){
            preStmt.setLong(1, id);
            preStmt.execute();
        } catch (SQLException e) {
            System.out.println("SQL error occured 4");
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Connection conn = Util.connect();
            Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e){
            System.out.println("SQL error occured 5");
        }
        return userList;
    }
    public void cleanUsersTable() {
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("SQL error occured 6");
        }
    }
}

   /* public class UserDaoJDBCImpl implements UserDao {


        public void createUsersTable() {
            String createTable = "CREATE TABLE IF NOT EXISTS Users (id BIGINT NOT NULL AUTO_INCREMENT primary key, " +
                    "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL);";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(createTable)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица создана успешно");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void dropUsersTable() {
            String dropTable = "DROP TABLE IF EXISTS Users";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(dropTable)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица удалена успешно");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void saveUser(String name, String lastName, byte age) {
            String saveUser = "INSERT INTO Users (name, lastName, age) VALUES (?, ? , ?)";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveUser)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                System.out.println("User с именем- " + name + " добавлен в базу данных");
            } catch (SQLException throwables) {
                System.out.println("Ошибка при добавление User");
                throwables.printStackTrace();
            }
        }


        public void removeUserById(long id) {
            String removeUser = "DELETE FROM Users WHERE ID = ? ";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeUser)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                System.out.println("User удален успешно");
            } catch (SQLException throwables) {
                System.out.println("Ошибка при удаление User");
                throwables.printStackTrace();
            }
        }

        public List<User> getAllUsers() {
            List<User> userList = new ArrayList<>();
            String getAllUsers = "SELECT name, lastName, age FROM Users";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(getAllUsers)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    userList.add(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return userList;
        }

        public void cleanUsersTable() {
            String cleanUsers = "DELETE FROM Users";
            try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(cleanUsers)) {
                preparedStatement.executeUpdate();
                System.out.println("Очистка таблицы проведена успешно");

            } catch (SQLException e) {
                System.out.println("Ошибка при очистке таблицы");
                e.printStackTrace();
            }
        }

package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

        public class Util {

            public static Connection getConnection() {
                final String URL = "jdbc:mysql://localhost:3306/my_schema?autoReconnect=true&useSSL=false&serverTimezone=UTC";
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


   /* private final String CREATE_TABLE = "CREATE TABLE `mydbtest`.`users` " +
            "(`id` BIGINT NOT NULL AUTO_INCREMENT," +
            " `name` VARCHAR(45) NOT NULL," +
            " `lastName` VARCHAR(45) NOT NULL," +
            " `age` TINYINT NOT NULL," +
            " PRIMARY KEY (`id`))";

    private final String DELETE_TABLE = "DROP TABLE IF EXISTS users";
    private final String SAVE_NEW_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String CLEAR_TABLE = "DELETE FROM users";
    private final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Util bdServicer;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            bdServicer = new Util();
            preparedStatement = bdServicer.getPreparedStatement(CREATE_TABLE);
            preparedStatement.executeUpdate();
// System.out.println("Таблица успешна создана");
            bdServicer.connectionCloser();
        } catch (SQLException e) {
            System.out.println("Таблица уже существует");
        }
    }

    public void dropUsersTable() {
        try {
            bdServicer = new Util();
            preparedStatement = bdServicer.getPreparedStatement(DELETE_TABLE);
            preparedStatement.executeUpdate();
// System.out.println("Таблица успешно удалена");
            bdServicer.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with delete table");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            bdServicer = new Util();
            preparedStatement = bdServicer.getPreparedStatement(SAVE_NEW_USER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " успешно добавлен в базу данных");
            bdServicer.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with save");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            bdServicer = new Util();
            preparedStatement = bdServicer.getPreparedStatement(DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
// System.out.println("Пользователь успешно удалён");
            bdServicer.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with delete table");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            bdServicer = new Util();
            preparedStatement = bdServicer.getPreparedStatement(GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
// System.out.println("Все пользователи получены");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
            bdServicer.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with create");
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try {
            bdServicer = new Util();
            preparedStatement = bdServicer.getPreparedStatement(CLEAR_TABLE);
            preparedStatement.executeUpdate();
// System.out.println("Таблица успешно очищена");
            bdServicer.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with cleaning table");
            e.printStackTrace();
        }
    }

    */

