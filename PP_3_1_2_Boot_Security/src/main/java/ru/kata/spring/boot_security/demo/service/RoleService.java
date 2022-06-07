package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAll();

    public Role findByRole(String role);

    public void saveRole(Role role);

    Set<Role> findByRoles(Set<Role> roles);
}
