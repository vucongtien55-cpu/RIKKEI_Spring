package org.example.quanlykhoahoc.repositories;

import org.example.quanlykhoahoc.entities.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lessons, Integer> {
    // API 16: Lấy danh sách bài học của một khóa học theo thứ tự (chỉ lấy bài đã PUBLISHED)
    List<Lessons> findByCourse_CourseIdAndIsPublishedTrueOrderByOrderIndexAsc(Integer courseId);

    // Dùng cho Admin/Teacher xem tất cả bài học trong khóa học
    List<Lessons> findByCourse_CourseIdOrderByOrderIndexAsc(Integer courseId);
}