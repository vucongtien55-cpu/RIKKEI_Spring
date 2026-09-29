package org.example.quanlykhoahoc.services;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.dtos.responses.*;
import org.example.quanlykhoahoc.entities.*;
import org.example.quanlykhoahoc.exceptions.AppException;
import org.example.quanlykhoahoc.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // API 10, 28, 29, 32: Lấy danh sách, tìm kiếm và lọc
    public List<CourseDetailResponse> searchCourses(String keyword, String status, Integer teacherId) {
        CourseStatus courseStatus = (status != null) ? CourseStatus.valueOf(status.toUpperCase()) : null;

        return courseRepository.searchCourses(keyword, courseStatus, teacherId).stream()
                .map(this::mapToDetailResponse)
                .collect(Collectors.toList());
    }

    // API 11: Chi tiết khóa học (Chỉ lấy bài học PUBLISHED)
    public CourseDetailResponse getCourseDetail(Integer courseId) {
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new AppException("Không tìm thấy khóa học này", HttpStatus.NOT_FOUND));

        List<LessonSummaryResponse> publishedLessons = course.getLessons().stream()
                .filter(Lessons::getIsPublished) // Chỉ lấy bài học đã xuất bản
                .map(l -> LessonSummaryResponse.builder()
                        .lessonId(l.getLessonId())
                        .title(l.getTitle())
                        .orderIndex(l.getOrderIndex())
                        .build())
                .collect(Collectors.toList());

        CourseDetailResponse response = mapToDetailResponse(course);
        response.setLessons(publishedLessons);
        return response;
    }

    // API 12: Tạo khóa học mới (Mặc định DRAFT)
    public CourseDetailResponse createCourse(String title, String description, BigDecimal price, Integer teacherId) {
        Users teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Courses course = Courses.builder()
                .title(title)
                .description(description)
                .price(price)
                .teacher(teacher)
                .status(CourseStatus.DRAFT) // Trạng thái ban đầu
                .build();

        return mapToDetailResponse(courseRepository.save(course));
    }

    // API 13: Cập nhật thông tin
    public CourseDetailResponse updateCourse(Integer id, Courses request) {
        Courses course = courseRepository.findById(id).orElseThrow();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        return mapToDetailResponse(courseRepository.save(course));
    }

    // API 14: Đổi trạng thái
    public void updateStatus(Integer id, String status) {
        Courses course = courseRepository.findById(id).orElseThrow();
        course.setStatus(CourseStatus.valueOf(status.toUpperCase()));
        courseRepository.save(course);
    }

    // API 15: Xóa khóa học
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    private CourseDetailResponse mapToDetailResponse(Courses course) {
        return CourseDetailResponse.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice().doubleValue())
                .teacherName(course.getTeacher().getFullName())
                .status(course.getStatus().name())
                .build();
    }
}