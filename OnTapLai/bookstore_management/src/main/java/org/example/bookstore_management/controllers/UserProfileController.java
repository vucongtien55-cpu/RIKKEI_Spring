package org.example.bookstore_management.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.UserProfile;
import org.example.bookstore_management.services.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    // Lấy tất cả UserProfile
    @GetMapping
    public List<UserProfile> getAll() {
        return userProfileService.getAll();
    }

    // Lấy UserProfile theo ID
    @GetMapping("/{id}")
    public UserProfile getById(@PathVariable Long id) {
        return userProfileService.getById(id);
    }

    // Tạo UserProfile
    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.crateUserProfile(userProfile);
    }

    // Cập nhật UserProfile
    @PutMapping("/{id}")
    public UserProfile updateUserProfile(
            @PathVariable Long id,
            @RequestBody UserProfile userProfile) {

        return userProfileService.updateUserProfile(id, userProfile);
    }

    // Xóa UserProfile
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
    }
}