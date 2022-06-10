package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Util {

    UserService userService;
    RoleService roleService;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createAdminAndUser() {
        User user = new User("user", "user", "user@mail.com", 13);
        User admin = new User("admin", "admin", "admin@mail.com", 14);
        Role roleUser = new Role(2L, "ROLE_USER");
        Role roleAdmin = new Role(1L, "ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();

        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);

        roles.add(roleUser);
        user.setRoles(roles);
        userService.saveUser(user);

        roles.add(roleAdmin);
        admin.setRoles(roles);
        userService.saveUser(admin);
    }
}
