package com.yourcompany.cau3.model.service;

import com.yourcompany.cau3.model.entity.User;
import com.yourcompany.cau3.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUser() {
        return userRepository.getUsers();
    }


}
