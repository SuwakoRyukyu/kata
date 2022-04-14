package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Butercup", "Camouflage", (byte) 20);
        userDao.saveUser("Bandersnatch", "Cummerbund", (byte) 25);
        userDao.saveUser("Billiardball", "Banglesnatch", (byte) 31);
        userDao.saveUser("Baseballmitt", "Cumberbund", (byte) 38);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.removeUserById(1);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
