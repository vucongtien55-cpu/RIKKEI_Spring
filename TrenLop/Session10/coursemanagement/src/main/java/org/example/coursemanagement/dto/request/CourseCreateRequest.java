package org.example.coursemanagement.dto.request;

import org.example.coursemanagement.entity.CourseStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseCreateRequest {

    @NotBlank(message = "Mã khóa học không được để trống")
    @Size(min = 4, max = 10, message = "Mã khóa học phải từ 4 đến 10 ký tự")
    private String code;

    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(max = 150, message = "Tên khóa học tối đa 150 ký tự")
    private String title;

    private String description;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = true, message = "Giá phải lớn hơn hoặc bằng 0")
    private BigDecimal price;

    @NotNull(message = "Trạng thái không được để trống")
    private CourseStatus status;
}