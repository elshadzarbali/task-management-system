package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("User with email " + user.getEmail() + " already exists");
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("User with username " + user.getUsername() + " already exists");
        }
        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("User with email " + user.getEmail() + " already exists");
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("User with username " + user.getUsername() + " already exists");
        }
        user.setId(foundUser.getId());
        return userRepository.save(user);
    }
}
