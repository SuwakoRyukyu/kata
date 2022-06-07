package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    void saveUser(User user);

    User findById(Long id);

    void updateUser(Long id, User user);

    void removeUserById(Long id);
}

