package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

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
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Override
    public User getUser(int id) {
        return dao.getUser(id);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        dao.removeUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
