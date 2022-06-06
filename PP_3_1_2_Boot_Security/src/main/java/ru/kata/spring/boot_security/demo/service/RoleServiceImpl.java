package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findByRole(String role) {
        return roleDAO.findByRole(role);
    }

    @Override
    public Set<Role> findByRoles(Set<Role> roles) {
        Set<Role> rolesTemp = new HashSet<>();
        roles.forEach(role -> {
            if (findByRole(role.getRole()) != null) {
                rolesTemp.add(findByRole(role.getRole()));
            }
        });
        return rolesTemp;
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleDAO.save(role);

    }
}
