package ryukyu.suwako.spring.boot.fuwa.dao;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ryukyu.suwako.spring.boot.fuwa.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User readUser(int id) {
        return entityManager.find(User.class, id);
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
        entityManager.merge(user);
    }
}
