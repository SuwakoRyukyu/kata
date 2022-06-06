package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUsername(String username) {
        User user;
        try {
            user = entityManager
                    .createQuery("from User as user where user.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        return entityManager
                .createQuery("from User as user where user.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void removeUserById(Long id) {
        entityManager
                .createQuery("delete from User as user where user.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void saveUser(User user) {
        if (findByUsername(user.getUsername()) != null) {
            System.out.println("User is already exist");
        } else {
            entityManager.persist(user);
        }
    }
}
