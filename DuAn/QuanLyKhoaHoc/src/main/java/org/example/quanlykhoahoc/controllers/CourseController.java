package org.example.quanlykhoahoc.controllers;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.dtos.responses.CourseDetailResponse;
import org.example.quanlykhoahoc.entities.Courses;
import org.example.quanlykhoahoc.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    // API 10, 28, 29, 32 tổng hợp: Danh sách, Tìm kiếm, Lọc
    @GetMapping
    public ResponseEntity<List<CourseDetailResponse>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer teacher_id) {
        return ResponseEntity.ok(courseService.searchCourses(search, status, teacher_id));
    }

    // API 11: Chi tiết khóa học
    // SỬA LẠI TRONG CourseController.java
    @GetMapping("/{course_id}")
    public ResponseEntity<CourseDetailResponse> getById(@PathVariable Integer course_id) {
        // Phải gọi hàm getCourseDetail để lấy đầy đủ thông tin và danh sách bài học
        CourseDetailResponse response = courseService.getCourseDetail(course_id);
        return ResponseEntity.ok(response);
    }

    // API 12: Tạo khóa học (Chỉ ADMIN)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(courseService.createCourse(
                (String) body.get("title"),
                (String) body.get("description"),
                new java.math.BigDecimal(body.get("price").toString()),
                (Integer) body.get("teacher_id")
        ));
    }

    // API 13: Cập nhật khóa học
    @PutMapping("/{course_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Integer course_id, @RequestBody Courses course) {
        return ResponseEntity.ok(courseService.updateCourse(course_id, course));
    }

    // API 14: Đổi trạng thái (DRAFT, PUBLISHED, ARCHIVED)
    @PutMapping("/{course_id}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Integer course_id, @RequestBody Map<String, String> body) {
        courseService.updateStatus(course_id, body.get("status"));
        return ResponseEntity.ok("Cập nhật trạng thái thành công");
    }

    // API 15: Xóa khóa học
    @DeleteMapping("/{course_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer course_id) {
        courseService.deleteCourse(course_id);
        return ResponseEntity.ok("Đã xóa khóa học");
    }
}