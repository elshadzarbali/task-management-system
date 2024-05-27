package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUser(int id);
    User updateUser(Integer id, User user);
}
