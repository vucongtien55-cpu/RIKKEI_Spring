package org.example.quanlykhoahoc.controllers;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.entities.Enrollments;
import org.example.quanlykhoahoc.services.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    // API 22: Danh sách khóa học đã đăng ký
    @GetMapping
    public ResponseEntity<List<Enrollments>> getMyEnrollments(Principal principal) {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments(principal.getName()));
    }

    // API 23: Đăng ký khóa học mới
    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody Map<String, Integer> body, Principal principal) {
        return ResponseEntity.ok(enrollmentService.enrollInCourse(body.get("course_id"), principal.getName()));
    }

    // API 24: Chi tiết thông tin đăng ký & tiến độ
    @GetMapping("/{enrollment_id}")
    public ResponseEntity<Enrollments> getDetail(@PathVariable Integer enrollment_id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentDetail(enrollment_id));
    }

    // API 25: Đánh dấu hoàn thành bài học
    @PutMapping("/{enrollment_id}/complete_lesson/{lesson_id}")
    public ResponseEntity<?> completeLesson(
            @PathVariable Integer enrollment_id,
            @PathVariable Integer lesson_id) {
        enrollmentService.completeLesson(enrollment_id, lesson_id);
        return ResponseEntity.ok("Đã cập nhật tiến độ bài học");
    }
}