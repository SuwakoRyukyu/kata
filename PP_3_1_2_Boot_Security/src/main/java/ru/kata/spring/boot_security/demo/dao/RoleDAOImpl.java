package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

@Component
public class RoleDAOImpl implements RoleDAO {

    private final EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findByRole(String role) {
        try {
            return entityManager
                    .createQuery("from Role as role where role.role = :role", Role.class)
                    .setParameter("role", role)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        return entityManager
                .createQuery("from Role", Role.class)
                .getResultList();
    }

    @Override
    public void save(Role role) {
        if (findByRole(role.getRole()) != null) {
            System.out.println("Role is already exist");
        } else {
            entityManager.persist(role);
        }
    }
}
