package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.reposotory.RoleRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public void saveRole(Role role) {
        if (findByRole(role.getRole()) != null) {
            System.out.println("User is already exist");
        } else {
            roleRepository.save(role);
        }
    }

    @Override
    public Set<Role> findByRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> roleRepository.findByRole(role.getRole()))
                .collect(Collectors.toSet());
    }
}
