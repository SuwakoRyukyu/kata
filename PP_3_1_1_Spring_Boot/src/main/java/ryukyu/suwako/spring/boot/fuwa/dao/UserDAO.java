package ryukyu.suwako.spring.boot.fuwa.dao;

import org.springframework.stereotype.Component;
import ryukyu.suwako.spring.boot.fuwa.entity.User;

import java.util.List;

@Component
public interface UserDAO {
    void createUser(User user);
    List<User> readUsers();
    User readUser(int id);
    void removeUser(User user);
    void updateUser(int id, User user);
}

