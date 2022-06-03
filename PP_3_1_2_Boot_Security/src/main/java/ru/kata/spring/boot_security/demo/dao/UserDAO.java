package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface UserDAO {

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    void updateUser(User user);

    void removeUserById(Long id);

    void saveUser(User user);
}
