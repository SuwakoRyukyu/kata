package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

public class BasicUserAndAdmin {
/*

    @Autowired
    UserService userService;
    @PostConstruct
    public void createUserAndAdmin() {
        User user = new User("user", "user", "govno", 13);
        User admin = new User("admin", "admin", "mocha", 12);
        Role role = new Role("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.(user);

 */
}
