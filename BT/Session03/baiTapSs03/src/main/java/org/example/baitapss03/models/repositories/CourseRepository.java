package org.example.baitapss03.models.repositories;
import org.example.baitapss03.models.entities.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(101L, "Lập trình Java Spring Boot", "ACTIVE", 1L));
        courses.add(new Course(102L, "Thiết kế Web ReactJS", "ACTIVE", 2L));
    }

    // 1. Lấy tất cả
    public List<Course> findAll() {
        return courses;
    }

    // 2. Tìm theo ID
    public Course findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 3. Tạo mới
    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    // 4. Cập nhật
    public Course update(Long id, Course updatedCourse) {
        Course existing = findById(id);
        if (existing != null) {
            existing.setTitle(updatedCourse.getTitle());
            existing.setStatus(updatedCourse.getStatus());
            existing.setInstructorId(updatedCourse.getInstructorId());
            return existing;
        }
        return null;
    }

    // 5. Xóa theo ID
    public Course deleteById(Long id) {
        Course existing = findById(id);
        if (existing != null) {
            courses.remove(existing);
            return existing;
        }
        return null;
    }
}
