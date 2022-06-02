package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
public class UserRESTController {
    private final UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    public List<User> userList(Model model) {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("/api/v1/user")
    public User currentUser() {
        return userService.getAuthUser();
    }

    @GetMapping("/api/v1/users/{id}")
    public User apiFindByID(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @PostMapping("/api/v1/users/update/{id}")
    public HttpStatus apiUpdateUser(@PathVariable("id") long id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/api/v1/users")
    public HttpStatus apiAddNewUser(@RequestBody User user) {
        try {
            System.out.println(user);
            System.out.println(user.getRoles());
            user.getRoles().forEach(System.out::println);
            userService.saveUser(user);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/api/v1/users/delete/{id}")
    public HttpStatus apiDeleteUser(@PathVariable("id") long id) {
        try {
            userService.removeUserById(id);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}
