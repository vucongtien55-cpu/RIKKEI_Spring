package org.example.coursemanagement.dto.response;

import org.example.coursemanagement.entity.CourseStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CourseResponse {
    private Long id;
    private String code;
    private String title;
    private String description;
    private BigDecimal price;
    private CourseStatus status;
}