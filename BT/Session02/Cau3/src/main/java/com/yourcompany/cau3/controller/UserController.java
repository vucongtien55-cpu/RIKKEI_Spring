package com.yourcompany.cau3.controller;

import com.yourcompany.cau3.model.entity.User;
import com.yourcompany.cau3.model.repository.UserRepository;
import com.yourcompany.cau3.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(required = false) String search) {

        List<User> users = userService.getUser();

        if (search != null && !search.isBlank()) {
            users = users.stream()
                    .filter(user -> user.getUserName()
                            .toLowerCase()
                            .contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(users);
    }
}
