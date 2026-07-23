package org.example.coursemanagement.service.impl;

import org.example.coursemanagement.dto.request.CourseCreateRequest;
import org.example.coursemanagement.dto.request.CourseUpdateRequest;
import org.example.coursemanagement.dto.response.CourseResponse;
import org.example.coursemanagement.entity.Course;
import org.example.coursemanagement.entity.CourseStatus;
import org.example.coursemanagement.exception.DuplicateResourceException;
import org.example.coursemanagement.exception.ResourceNotFoundException;
import org.example.coursemanagement.repository.CourseRepository;
import org.example.coursemanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Page<CourseResponse> getAllCourses(CourseStatus status, Pageable pageable) {
        Page<Course> courses;
        if (status != null) {
            courses = courseRepository.findByStatus(status, pageable);
        } else {
            courses = courseRepository.findAll(pageable);
        }
        return courses.map(this::mapToResponse);
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học có ID: " + id));
        return mapToResponse(course);
    }

    @Override
    @Transactional
    public CourseResponse createCourse(CourseCreateRequest request) {
        if (courseRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Mã khóa học '" + request.getCode() + "' đã tồn tại");
        }

        Course course = Course.builder()
                .code(request.getCode())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .status(request.getStatus())
                .build();

        return mapToResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public CourseResponse updateCourse(Long id, CourseUpdateRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học có ID: " + id));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setStatus(request.getStatus());

        return mapToResponse(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học có ID: " + id));

        // Xóa mềm: Chuyển trạng thái sang ARCHIVED
        course.setStatus(CourseStatus.ARCHIVED);
        courseRepository.save(course);
    }

    private CourseResponse mapToResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .code(course.getCode())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .status(course.getStatus())
                .build();
    }
}