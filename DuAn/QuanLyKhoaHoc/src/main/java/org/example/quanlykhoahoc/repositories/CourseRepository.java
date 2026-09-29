package org.example.quanlykhoahoc.repositories;

import org.example.quanlykhoahoc.entities.Courses;
import org.example.quanlykhoahoc.entities.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

    // Tìm kiếm theo từ khóa trong tiêu đề hoặc mô tả và lọc theo status
    @Query("SELECT c FROM Courses c WHERE " +
            "(:keyword IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:teacherId IS NULL OR c.teacher.userId = :teacherId)")
    List<Courses> searchCourses(@Param("keyword") String keyword,
                                @Param("status") CourseStatus status,
                                @Param("teacherId") Integer teacherId);

    // Lấy danh sách khóa học theo trạng thái (Dùng cho API 10)
    List<Courses> findAllByStatus(CourseStatus status);
}