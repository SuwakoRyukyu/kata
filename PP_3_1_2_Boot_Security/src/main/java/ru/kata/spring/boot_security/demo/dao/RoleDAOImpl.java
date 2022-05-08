package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findByRole(String role) {
        return entityManager
                .createQuery("from Role as role where role.role = :role", Role.class)
                .getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        return entityManager
                .createQuery("from Role", Role.class)
                .getResultList();
    }
}
