package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.Set;

@RestController
public class RoleRESTController {

    private final RoleService roleService;

    @Autowired
    public RoleRESTController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/api/v1/roles")
    public Set<Role> userList(Model model) {
        return roleService.findAll();
    }
}
