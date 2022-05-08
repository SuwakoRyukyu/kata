package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

@Repository
public interface RoleDAO {

    Role findByRole(String role);
    List<Role> findAll();
}
