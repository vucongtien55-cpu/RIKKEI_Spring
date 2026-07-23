package org.example.coursemanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonResponse {
    private Long id;
    private String title;
    private String videoUrl;
    private Integer duration;
    private Long courseId;
}