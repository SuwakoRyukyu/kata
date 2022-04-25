package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User readUser(int id) {
        return entityManager
                .createQuery("from User as user where user.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    @Override
    public List<User> readUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void removeUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void updateUser(int id, User user) {
        User tempUser = readUser(id);
        tempUser.setName(user.getName());
        tempUser.setAge(user.getAge());
        tempUser.setMail(user.getMail());
    }
}
