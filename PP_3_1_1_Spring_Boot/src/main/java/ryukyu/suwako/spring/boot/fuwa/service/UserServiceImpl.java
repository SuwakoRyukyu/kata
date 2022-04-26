package ryukyu.suwako.spring.boot.fuwa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ryukyu.suwako.spring.boot.fuwa.dao.UserDAO;
import ryukyu.suwako.spring.boot.fuwa.entity.User;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    private UserDAO dao;

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void createUser(User user) {
        dao.createUser(user);
    }

    @Override
    public List<User> readUsers() {
        return dao.readUsers();
    }

    @Override
    public User readUser(int id) {
        return dao.readUser(id);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        dao.removeUser(user);
    }

    @Transactional
    @Override
    public void updateUser(int id, User user) {
        dao.updateUser(id, user);
    }
}
