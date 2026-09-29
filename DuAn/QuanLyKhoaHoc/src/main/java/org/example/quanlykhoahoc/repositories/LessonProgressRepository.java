package org.example.quanlykhoahoc.repositories;

import org.example.quanlykhoahoc.entities.LessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonProgressRepository extends JpaRepository<LessonProgress, Integer> {
    // Tìm bản ghi tiến độ của một bài học cụ thể trong một lượt đăng ký
    Optional<LessonProgress> findByEnrollment_EnrollmentIdAndLesson_LessonId(Integer enrollmentId, Integer lessonId);

    // Đếm số bài học đã hoàn thành trong một lượt đăng ký
    long countByEnrollment_EnrollmentIdAndIsCompletedTrue(Integer enrollmentId);
}