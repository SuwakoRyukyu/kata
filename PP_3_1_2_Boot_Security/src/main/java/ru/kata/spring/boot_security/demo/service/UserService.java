package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

@Component
public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    void saveUser(User user);

    User findById(Long id);

    void updateUser(User user);

    public void removeUserById(Long id);
}
