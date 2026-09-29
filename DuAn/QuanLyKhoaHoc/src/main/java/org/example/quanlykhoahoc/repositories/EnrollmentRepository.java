package org.example.quanlykhoahoc.repositories;

import org.example.quanlykhoahoc.entities.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollments, Integer> {
    // API 22: Lấy danh sách khóa học sinh viên đã đăng ký
    List<Enrollments> findByStudent_Username(String username);

    // Kiểm tra xem sinh viên đã đăng ký khóa học này chưa (Tránh đăng ký trùng)
    Optional<Enrollments> findByStudent_UserIdAndCourse_CourseId(Integer studentId, Integer courseId);
}