package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Butercup", "Camouflage", (byte) 20);
        userService.saveUser("Bandersnatch", "Cummerbund", (byte) 25);
        userService.saveUser("Billiardball", "Banglesnatch", (byte) 31);
        userService.saveUser("Baseballmitt", "Cumberbund", (byte) 38);
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(1);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
