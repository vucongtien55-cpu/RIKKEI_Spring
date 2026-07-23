package com.yourcompany.cau4.controllers;

import com.yourcompany.cau4.dtos.ApiResponse;
import com.yourcompany.cau4.services.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {

    @Autowired
    private StudentEnrollmentService studentEnrollmentService;

    // POST /students-enrollments
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enrollStudent(
            @RequestParam Long studentId,
            @RequestParam Long courseId) throws Throwable {
        studentEnrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.ok(ApiResponse.success("Đăng ký sinh viên vào khóa học thành công!"));
    }
}
