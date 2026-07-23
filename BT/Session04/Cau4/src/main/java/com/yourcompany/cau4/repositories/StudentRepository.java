package com.yourcompany.cau4.repositories;

import com.yourcompany.cau4.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
