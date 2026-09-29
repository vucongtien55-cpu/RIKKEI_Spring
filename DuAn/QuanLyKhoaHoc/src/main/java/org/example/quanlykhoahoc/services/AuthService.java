package org.example.quanlykhoahoc.services;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.configs.JwtUtils;
//import org.example.quanlykhoahoc.dtos.*;
import org.example.quanlykhoahoc.dtos.requests.LoginRequest;
import org.example.quanlykhoahoc.dtos.responses.UserResponse;
import org.example.quanlykhoahoc.entities.Users;
import org.example.quanlykhoahoc.exceptions.AppException;
import org.example.quanlykhoahoc.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // Logic API 1: Login
    public String login(LoginRequest request) {
        // Tìm user
        Users user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException("Tên đăng nhập không tồn tại", HttpStatus.UNAUTHORIZED));

        System.out.println("--- DEBUG LOGIN ---");
        System.out.println("Mật khẩu gõ ở Postman: [" + request.getPassword() + "]");
        System.out.println("Mật khẩu Java lấy từ DB:   [" + user.getPasswordHash() + "]");
        System.out.println("-------------------");

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new AppException("Mật khẩu không chính xác", HttpStatus.UNAUTHORIZED);
        }

        // Tạo token
        return jwtUtils.generateToken(user.getUsername());
    }

    // Chuyển đổi Entity sang DTO
    public UserResponse mapToResponse(Users user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .isActive(user.getIsActive())
                .build();
    }
}