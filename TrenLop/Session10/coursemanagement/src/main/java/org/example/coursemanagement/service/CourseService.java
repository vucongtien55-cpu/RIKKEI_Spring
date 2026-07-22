package org.example.coursemanagement.service;

import org.example.coursemanagement.dto.request.CourseCreateRequest;
import org.example.coursemanagement.dto.request.CourseUpdateRequest;
import org.example.coursemanagement.dto.response.CourseResponse;
import org.example.coursemanagement.entity.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CourseResponse> getAllCourses(CourseStatus status, Pageable pageable);
    CourseResponse getCourseById(Long id);
    CourseResponse createCourse(CourseCreateRequest request);
    CourseResponse updateCourse(Long id, CourseUpdateRequest request);
    void deleteCourse(Long id);
}