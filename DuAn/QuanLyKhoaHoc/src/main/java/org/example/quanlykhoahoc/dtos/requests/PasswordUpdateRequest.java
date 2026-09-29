package org.example.quanlykhoahoc.dtos.requests;

import lombok.Data;

@Data
public class PasswordUpdateRequest {
    private String oldPassword;
    private String newPassword;
}