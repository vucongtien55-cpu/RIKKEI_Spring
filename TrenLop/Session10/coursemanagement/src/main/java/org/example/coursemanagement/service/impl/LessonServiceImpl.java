package org.example.coursemanagement.service.impl;

import org.example.coursemanagement.dto.response.LessonResponse;
import org.example.coursemanagement.entity.Lesson;
import org.example.coursemanagement.exception.ResourceNotFoundException;
import org.example.coursemanagement.repository.CourseRepository;
import org.example.coursemanagement.repository.LessonRepository;
import org.example.coursemanagement.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<LessonResponse> getLessonsByCourseId(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Không tìm thấy khóa học có ID: " + courseId);
        }

        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        return lessons.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private LessonResponse mapToResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .videoUrl(lesson.getVideoUrl())
                .duration(lesson.getDuration())
                .courseId(lesson.getCourse().getId())
                .build();
    }
}