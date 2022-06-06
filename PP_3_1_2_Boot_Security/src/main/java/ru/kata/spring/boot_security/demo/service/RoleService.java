package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

@Component
public interface RoleService {

    List<Role> findAll();

     Role findByRole(String role);

     Set<Role> findByRoles(Set<Role> roles);

     void save(Role role);
}
