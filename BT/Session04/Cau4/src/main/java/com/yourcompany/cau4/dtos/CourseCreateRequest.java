package com.yourcompany.cau4.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {
    private String title;
    private String status; // ví dụ: "ACTIVE", "PENDING"
    private Long instructorId;
}