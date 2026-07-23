package com.yourcompany.cau4.repositories;

import com.yourcompany.cau4.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
