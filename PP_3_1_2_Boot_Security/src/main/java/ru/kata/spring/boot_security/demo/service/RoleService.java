package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.reposotory.RoleRepository;

import java.util.Set;

public interface RoleService {
    @Autowired
    void setRoleRepository(RoleRepository roleRepository);

    Set<Role> findAll();

    Role findByRole(String role);

    Boolean saveRole(Role role);
}
