package org.example.coursemanagement.service;

import org.example.coursemanagement.dto.response.LessonResponse;
import java.util.List;

public interface LessonService {
    List<LessonResponse> getLessonsByCourseId(Long courseId);
}