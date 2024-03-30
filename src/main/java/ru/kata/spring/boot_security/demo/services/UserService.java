package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();

    public User findOne(int id);

    public void save(User user);

    public void update(int id, User updatedUser);

    public void delete(int id);
}
