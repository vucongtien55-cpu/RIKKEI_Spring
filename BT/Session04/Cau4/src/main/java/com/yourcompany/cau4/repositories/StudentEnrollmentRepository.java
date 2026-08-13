package com.yourcompany.cau4.repositories;

import com.yourcompany.cau4.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
}
