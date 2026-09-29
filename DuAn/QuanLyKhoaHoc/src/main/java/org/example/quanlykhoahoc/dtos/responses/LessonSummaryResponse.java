package org.example.quanlykhoahoc.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonSummaryResponse {
    private Integer lessonId;
    private String title;
    private Integer orderIndex;
}