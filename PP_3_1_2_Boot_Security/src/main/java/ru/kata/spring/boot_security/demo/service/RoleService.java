package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

@Component
public interface RoleService {

    List<Role> findAll();

     Role findByRole(String role);

     void save(Role role);
}
