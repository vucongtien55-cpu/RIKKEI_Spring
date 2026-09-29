package org.example.quanlykhoahoc.controllers;

import lombok.RequiredArgsConstructor;
import org.example.quanlykhoahoc.entities.Lessons;
import org.example.quanlykhoahoc.services.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    // API 16: Danh sách bài học của khóa học (Public/Auth)
    @GetMapping("/courses/{course_id}/lessons")
    public ResponseEntity<List<Lessons>> getLessonsByCourse(@PathVariable Integer course_id) {
        return ResponseEntity.ok(lessonService.getPublishedLessons(course_id));
    }

    // API 17 & 44: Chi tiết bài học / Xem trước nội dung
    @GetMapping("/lessons/{lesson_id}")
    public ResponseEntity<Lessons> getLessonDetail(@PathVariable Integer lesson_id) {
        return ResponseEntity.ok(lessonService.getLessonDetail(lesson_id));
    }

    // API 18: Thêm bài học mới (Admin hoặc Giảng viên phụ trách)
    @PostMapping("/courses/{course_id}/lessons")
    public ResponseEntity<?> createLesson(
            @PathVariable Integer course_id,
            @RequestBody Lessons lesson,
            Principal principal) {

        // THÊM DÒNG NÀY VÀO ĐỂ KIỂM TRA
        System.out.println(">>>>>>>>> ĐÃ CHẠY VÀO ĐÚNG HÀM CREATE LESSON RỒI! <<<<<<<<<");

        Lessons savedLesson = lessonService.createLesson(course_id, lesson, principal.getName());
        return ResponseEntity.ok(savedLesson);
    }

    // API 19: Cập nhật bài học
    @PutMapping("/lessons/{lesson_id}")
    public ResponseEntity<?> updateLesson(
            @PathVariable Integer lesson_id,
            @RequestBody Lessons lesson,
            Principal principal) {
        return ResponseEntity.ok(lessonService.updateLesson(lesson_id, lesson, principal.getName()));
    }

    // API 20: Cập nhật trạng thái is_published
    @PutMapping("/lessons/{lesson_id}/publish")
    public ResponseEntity<?> publishLesson(
            @PathVariable Integer lesson_id,
            @RequestBody Map<String, Boolean> body,
            Principal principal) {
        lessonService.togglePublish(lesson_id, body.get("is_published"), principal.getName());
        return ResponseEntity.ok("Cập nhật trạng thái thành công");
    }

    // API 21: Xóa bài học
    @DeleteMapping("/lessons/{lesson_id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Integer lesson_id, Principal principal) {
        lessonService.deleteLesson(lesson_id, principal.getName());
        return ResponseEntity.ok("Xóa bài học thành công");
    }
}