package org.example.quanlykhoahoc.dtos.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String email;
}