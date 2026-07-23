package com.yourcompany.cau4.controllers;

import com.yourcompany.cau4.dtos.ApiResponse;
import com.yourcompany.cau4.dtos.StudentCreateRequest;
import com.yourcompany.cau4.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST /students
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createStudent(@RequestBody StudentCreateRequest req) {
        studentService.createStudent(req);
        return ResponseEntity.ok(ApiResponse.success("Tạo sinh viên thành công!"));
    }
}