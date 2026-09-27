package org.example.bookstore_management.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.User;
import org.example.bookstore_management.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //Lấy tất cả danh sách
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    //Lấy user theo id
    @GetMapping("{id}")
    public User getById(@PathVariable long id){
        return userService.getById(id);
    }

    //Thêm user
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    //Sửa user
    @PutMapping("{id}")
    public User updateUser(
            @PathVariable long id,
            @RequestBody User user
    ){
        return userService.updateUser(id, user);
    }

    //Xóa
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}
