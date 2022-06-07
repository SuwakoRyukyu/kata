package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUsers(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roles", roleService.findAll());
        modelMap.addAttribute("users", userService.findAll());
        modelMap.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "admin_page/admin";
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
        return "admin_page/new";
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
        return "admin_page/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String deleteUserForm(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
