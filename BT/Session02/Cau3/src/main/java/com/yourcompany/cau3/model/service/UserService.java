package com.yourcompany.cau3.model.service;

import com.yourcompany.cau3.model.entity.User;
import com.yourcompany.cau3.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUser(int id, User updatedData) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            userRepository.update(existingUser, updatedData);
            return existingUser;
        }
        return null;
    }

    public boolean deleteUserById(int id) {
        return userRepository.deleteById(id);
    }
}