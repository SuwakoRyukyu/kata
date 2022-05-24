package web.service;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

@Component
public interface UserService {
    void createUser(User user);
    List<User> getUsers();
    User getUser(int id);
    void removeUser(User user);
    void updateUser(User user);
}
