package org.example.quanlykhoahoc.dtos.responses;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CourseDetailResponse {
    private Integer courseId;
    private String title;
    private String description;
    private Double price;
    private String teacherName;
    private String status;
    private List<LessonSummaryResponse> lessons; // Danh sách các bài học PUBLISHED
}