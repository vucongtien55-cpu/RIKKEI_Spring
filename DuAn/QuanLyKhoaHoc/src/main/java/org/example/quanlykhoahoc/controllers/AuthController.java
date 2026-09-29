package org.example.quanlykhoahoc.controllers;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.configs.JwtUtils;
import org.example.quanlykhoahoc.dtos.requests.LoginRequest;
import org.example.quanlykhoahoc.entities.Users;
import org.example.quanlykhoahoc.repositories.UserRepository;
import org.example.quanlykhoahoc.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @PostMapping("/login") // API 1
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/verify") // API 2
    public ResponseEntity<?> verify(@RequestParam String token) {
        return ResponseEntity.ok(jwtUtils.validateToken(token));
    }

    @GetMapping("/me") // API 3
    public ResponseEntity<?> getMe(Principal principal) {
        Users user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(authService.mapToResponse(user));
    }
}