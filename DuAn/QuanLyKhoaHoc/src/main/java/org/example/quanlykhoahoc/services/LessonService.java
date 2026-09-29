package org.example.quanlykhoahoc.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.entities.Courses;
import org.example.quanlykhoahoc.entities.Lessons;
import org.example.quanlykhoahoc.entities.Role;
import org.example.quanlykhoahoc.entities.Users;
import org.example.quanlykhoahoc.repositories.CourseRepository;
import org.example.quanlykhoahoc.repositories.LessonRepository;
import org.example.quanlykhoahoc.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // Kiểm tra xem User có quyền thao tác trên khóa học này không (Admin hoặc đúng Giảng viên đó)
    private void validateOwnership(Integer courseId, String username) {
        Users user = userRepository.findByUsername(username).orElseThrow();
        Courses course = courseRepository.findById(courseId).orElseThrow();

        if (user.getRole() != Role.ADMIN && !course.getTeacher().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Bạn không có quyền quản lý bài học trong khóa học này");
        }
    }

    // API 16: Lấy danh sách bài học đã xuất bản
    public List<Lessons> getPublishedLessons(Integer courseId) {
        return lessonRepository.findByCourse_CourseIdAndIsPublishedTrueOrderByOrderIndexAsc(courseId);
    }

    // API 17: Lấy chi tiết bài học (Chỉ khi đã PUBLISHED)
    public Lessons getLessonDetail(Integer lessonId) {
        Lessons lesson = lessonRepository.findById(lessonId).orElseThrow();
        if (!lesson.getIsPublished()) {
            throw new RuntimeException("Bài học này chưa được công khai");
        }
        return lesson;
    }

    // API 18: Thêm bài học mới
    // TRONG LessonService.java
    @Transactional // Thêm cái này để đảm bảo dữ liệu được ghi xuống DB
    public Lessons createLesson(Integer courseId, Lessons lessonRequest, String username) {
        // 1. Kiểm tra quyền (Hàm này bạn đã viết rồi)
        validateOwnership(courseId, username);

        // 2. Tìm khóa học
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học"));

        // 3. Quan trọng: Gán khóa học vào bài học
        lessonRequest.setCourse(course);

        // 4. LƯU VÀO DATABASE
        return lessonRepository.save(lessonRequest);
    }

    // API 19: Cập nhật bài học
    public Lessons updateLesson(Integer lessonId, Lessons request, String username) {
        Lessons lesson = lessonRepository.findById(lessonId).orElseThrow();
        validateOwnership(lesson.getCourse().getCourseId(), username);

        lesson.setTitle(request.getTitle());
        lesson.setContentUrl(request.getContentUrl());
        lesson.setTextContent(request.getTextContent());
        lesson.setOrderIndex(request.getOrderIndex());

        return lessonRepository.save(lesson);
    }

    // API 20: Đổi trạng thái hiển thị
    public void togglePublish(Integer lessonId, Boolean status, String username) {
        Lessons lesson = lessonRepository.findById(lessonId).orElseThrow();
        validateOwnership(lesson.getCourse().getCourseId(), username);
        lesson.setIsPublished(status);
        lessonRepository.save(lesson);
    }

    // API 21: Xóa bài học
    public void deleteLesson(Integer lessonId, String username) {
        Lessons lesson = lessonRepository.findById(lessonId).orElseThrow();
        validateOwnership(lesson.getCourse().getCourseId(), username);
        lessonRepository.delete(lesson);
    }
}