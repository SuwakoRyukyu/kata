package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Util {

    private static UserService userService;
    private static RoleService roleService;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        Util.userService = userService;
        Util.roleService = roleService;
    }

    @PostConstruct
    public static void createAdminAndUser() {
        User user = new User("user", "user", "user@mail.ru", 13);
        User admin = new User("admin", "admin", "admin@mail.ru", 14);

        Set<Role> roles = new HashSet<>();

        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleUser);
        roles.add(roleUser);
        user.setRoles(roles);
        userService.saveUser(user);

        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.saveRole(roleAdmin);
        roles.add(roleAdmin);
        admin.setRoles(roles);
        userService.saveUser(admin);
    }
}