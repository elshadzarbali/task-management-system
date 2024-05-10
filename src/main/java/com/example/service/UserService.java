package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    User getUser(int id);
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUser(int id);
    User updateUser(User user);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}
