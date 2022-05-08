package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String showUser(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.findById(id));
        return "user";
    }

    @GetMapping("/new")
    public String createUserForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roles", roleService.findAll());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.findById(id));
        modelMap.addAttribute("roles", roleService.findAll());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String deleteUserForm(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
