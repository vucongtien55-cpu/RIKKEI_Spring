package org.example.quanlykhoahoc.services;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.dtos.requests.PasswordUpdateRequest;
import org.example.quanlykhoahoc.dtos.responses.UserResponse;
import org.example.quanlykhoahoc.entities.Role;
import org.example.quanlykhoahoc.entities.Users;
import org.example.quanlykhoahoc.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // API 31: Lấy danh sách kèm lọc status
    public List<UserResponse> getUsers(Boolean status) {
        List<Users> users;
        if (status != null) {
            users = userRepository.findAllByIsActive(status);
        } else {
            users = userRepository.findAll();
        }
        return users.stream().map(this::mapToResponse).toList();
    }

    // API 7: Cập nhật Role kèm ràng buộc
    public UserResponse updateRole(Integer targetUserId, String newRole) {
        Users targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Ràng buộc: Admin không được sửa ROLE của ADMIN khác
        if (targetUser.getRole() == Role.ADMIN) {
            throw new RuntimeException("Không được phép thay đổi vai trò của quản trị viên khác");
        }

        targetUser.setRole(Role.valueOf(newRole.toUpperCase()));
        return mapToResponse(userRepository.save(targetUser));
    }

    public void changePassword(Integer targetUserId, PasswordUpdateRequest request, String currentUsername) {
        Users targetUser = userRepository.findById(targetUserId).orElseThrow();
        Users currentUser = userRepository.findByUsername(currentUsername).orElseThrow();

        // Kiểm tra quyền: Nếu không phải Admin và cũng không phải chủ sở hữu (Owner)
        if (currentUser.getRole() != Role.ADMIN && !currentUser.getUserId().equals(targetUserId)) {
            throw new RuntimeException("Bạn không có quyền đổi mật khẩu của người khác");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), targetUser.getPasswordHash())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        targetUser.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(targetUser);
    }


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
