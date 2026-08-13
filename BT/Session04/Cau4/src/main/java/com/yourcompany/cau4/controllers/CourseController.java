package com.yourcompany.cau4.controllers;

import com.yourcompany.cau4.dtos.ApiResponse;
import com.yourcompany.cau4.dtos.CourseCreateRequest;
import com.yourcompany.cau4.dtos.CourseUpdateRequest;
import com.yourcompany.cau4.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // POST /courses
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCourse(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        return ResponseEntity.ok(ApiResponse.success("Tạo khóa học thành công!"));
    }

    // PUT /courses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật khóa học thành công!"));
    }
}