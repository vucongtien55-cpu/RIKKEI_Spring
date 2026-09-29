package org.example.quanlykhoahoc.controllers;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.dtos.requests.PasswordUpdateRequest;
import org.example.quanlykhoahoc.dtos.responses.UserResponse;
import org.example.quanlykhoahoc.entities.Users;
import org.example.quanlykhoahoc.repositories.UserRepository;
import org.example.quanlykhoahoc.services.AuthService;
import org.example.quanlykhoahoc.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // API 4 & 31: Lấy danh sách người dùng (Có thể lọc bằng ?status=true/false)
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) Boolean status) {
        return ResponseEntity.ok(userService.getUsers(status));
    }

    // API 7: Cập nhật Role
    @PutMapping("/users/{user_id}/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable Integer user_id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(userService.updateRole(user_id, body.get("role")));
    }

    // API 30: Đăng xuất
    // Lưu ý: JWT là stateless, Server không cần làm gì nhiều,
    // Frontend chỉ cần xóa Token. Tuy nhiên API này vẫn nên có để đúng chuẩn.
    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Đăng xuất thành công. Hãy xóa token ở phía client.");
    }

    // API 27: Đổi mật khẩu
    @PutMapping("/users/{user_id}/password")
    public ResponseEntity<?> changePassword(
            @PathVariable Integer user_id,
            @RequestBody PasswordUpdateRequest request,
            Principal principal) {
        userService.changePassword(user_id, request, principal.getName());
        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }
}