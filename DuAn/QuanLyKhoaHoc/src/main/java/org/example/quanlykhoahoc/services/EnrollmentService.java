package org.example.quanlykhoahoc.services;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.entities.*;
import org.example.quanlykhoahoc.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final LessonProgressRepository progressRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    // API 22: Lấy danh sách đăng ký của sinh viên hiện tại
    public List<Enrollments> getMyEnrollments(String username) {
        return enrollmentRepository.findByStudent_Username(username);
    }

    // API 23: Đăng ký khóa học mới
    public Enrollments enrollInCourse(Integer courseId, String username) {
        Users student = userRepository.findByUsername(username).orElseThrow();
        Courses course = courseRepository.findById(courseId).orElseThrow();

        // Kiểm tra nếu đã đăng ký rồi
        if (enrollmentRepository.findByStudent_UserIdAndCourse_CourseId(student.getUserId(), courseId).isPresent()) {
            throw new RuntimeException("Bạn đã đăng ký khóa học này rồi");
        }

        Enrollments enrollment = Enrollments.builder()
                .student(student)
                .course(course)
                .status(EnrollmentStatus.ENROLLED)
                .progressPercent(BigDecimal.ZERO)
                .build();

        return enrollmentRepository.save(enrollment);
    }

    // API 24: Chi tiết đăng ký
    public Enrollments getEnrollmentDetail(Integer enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).orElseThrow();
    }

    // API 25: Hoàn thành bài học & Cập nhật tiến độ %
    @Transactional
    public void completeLesson(Integer enrollmentId, Integer lessonId) {
        Enrollments enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow();
        Lessons lesson = lessonRepository.findById(lessonId).orElseThrow();

        // 1. Cập nhật hoặc tạo mới bản ghi LessonProgress
        LessonProgress progress = progressRepository.findByEnrollment_EnrollmentIdAndLesson_LessonId(enrollmentId, lessonId)
                .orElse(LessonProgress.builder().enrollment(enrollment).lesson(lesson).build());

        progress.setIsCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());
        progressRepository.save(progress);

        // 2. Tính toán lại % tiến độ
        // Tổng số bài học của khóa học
        long totalLessons = enrollment.getCourse().getLessons().size();
        // Số bài học đã hoàn thành
        long completedCount = progressRepository.countByEnrollment_EnrollmentIdAndIsCompletedTrue(enrollmentId);

        double percent = ((double) completedCount / totalLessons) * 100;
        enrollment.setProgressPercent(BigDecimal.valueOf(percent));

        // 3. Nếu đạt 100%, cập nhật trạng thái hoàn thành khóa học
        if (percent >= 100.0) {
            enrollment.setStatus(EnrollmentStatus.COMPLETED);
            enrollment.setCompletionDate(LocalDateTime.now());
        }

        enrollmentRepository.save(enrollment);
    }
}