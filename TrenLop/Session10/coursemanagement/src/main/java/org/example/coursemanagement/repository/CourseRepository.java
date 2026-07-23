package org.example.coursemanagement.repository;

import org.example.coursemanagement.entity.Course;
import org.example.coursemanagement.entity.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
    Page<Course> findByStatus(CourseStatus status, Pageable pageable);
}