package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userDAO.findAll();
    }
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with this username was not found");
        }
        return user;
    }

    public boolean saveUser(User user) {
        User userToFind = userDAO.findByUsername(user.getUsername());
        if (userToFind != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
        return true;
    }

    public User findById(Long id) {
        User user = userDAO.findById(id);
        return Objects.requireNonNullElseGet(user, User::new);
    }

    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    public void removeUserById(Long id) {
        userDAO.removeUserById(id);
    }
}
