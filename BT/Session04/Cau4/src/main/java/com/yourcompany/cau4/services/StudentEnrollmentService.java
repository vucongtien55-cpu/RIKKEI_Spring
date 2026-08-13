package com.yourcompany.cau4.services;

import com.yourcompany.cau4.entities.Course;
import com.yourcompany.cau4.entities.Student;
import com.yourcompany.cau4.entities.StudentEnrollment;
import com.yourcompany.cau4.repositories.CourseRepository;
import com.yourcompany.cau4.repositories.StudentEnrollmentRepository;
import com.yourcompany.cau4.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    // Bước 2: Triển khai enrollStudent
    public void enrollStudent(Long studentId, Long courseId) throws Throwable {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Sinh viên với ID: " + studentId));

        Course course = (Course) courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Khóa học với ID: " + courseId));

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        studentEnrollmentRepository.save(enrollment);
    }
}
