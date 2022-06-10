package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsername(String username);

    boolean saveUser(User user);

    User findById(Long id);

    boolean updateUser(Long id, User user);

    boolean removeUserById(Long id);

    User getAuthUser();
}
