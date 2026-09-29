package org.example.quanlykhoahoc.dtos.responses;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Integer userId;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private Boolean isActive;
}