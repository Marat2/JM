package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("A","B",(byte)1);
        System.out.println("User с именем – A добавлен в базу данных");
        userService.saveUser("A1","B1",(byte)11);
        System.out.println("User с именем – A1 добавлен в базу данных");
        userService.saveUser("A2","B2",(byte)12);
        System.out.println("User с именем – A2 добавлен в базу данных");
        userService.saveUser("A3","B3",(byte)13);
        System.out.println("User с именем – A3 добавлен в базу данных");
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
