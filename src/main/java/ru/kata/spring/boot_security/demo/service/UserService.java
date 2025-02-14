package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User getUserById(long id);

    Optional<User> show (String email);
}
