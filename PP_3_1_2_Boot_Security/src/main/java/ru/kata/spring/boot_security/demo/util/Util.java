package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Util {


    @Autowired
    private UserService userService;

    /*
    @PostConstruct
    public void createAdminAndUser() {


        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_ADMIN"));
        roles.add(new Role("ROLE_USER"));

        user.setUsername("adminCreatedInUtil");
        user.setPassword("$2a$12$kSmVkasZEwuIFNkFhaHlS.B4jSBOlAQsLXe7oDSbc0o9E7VWTUHsS");
        user.setEmail("admin@mail.com");
        user.setRoles(roles);
        userService.saveUser(user);
    }

     */
}
