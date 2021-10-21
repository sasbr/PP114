package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl serviceObject = new UserServiceImpl();
        serviceObject.createUsersTable();
        serviceObject.saveUser("Ivan", "Ivanov", (byte)23);
        serviceObject.saveUser("Alex", "Petrov", (byte)20);
        serviceObject.saveUser("Victor", "Sergeev", (byte)42);
        serviceObject.saveUser("Sergei", "Victorov", (byte)15);
        System.out.println(serviceObject.getAllUsers());
        serviceObject.cleanUsersTable();
        serviceObject.dropUsersTable();


    }
}
