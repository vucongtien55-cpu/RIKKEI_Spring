package org.example.baitapss03.controllers;
import org.example.baitapss03.models.entities.Instructor;
import org.example.baitapss03.models.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.baitapss03.models.entities.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors() {
        List<Instructor> list = instructorService.getAllInstructors();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách giảng viên thành công", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor != null) {
            return ResponseEntity.ok(ApiResponse.success("Lấy thông tin giảng viên thành công", instructor));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy giảng viên với ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody Instructor instructor) {
        Instructor created = instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo giảng viên thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        Instructor updated = instructorService.updateInstructor(id, instructor);
        if (updated != null) {
            return ResponseEntity.ok(ApiResponse.success("Cập nhật thông tin giảng viên thành công", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy giảng viên để cập nhật với ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Long id) {
        Instructor deleted = instructorService.deleteInstructorById(id);
        if (deleted != null) {
            return ResponseEntity.ok(ApiResponse.success("Xóa giảng viên thành công", deleted));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Không tìm thấy giảng viên để xóa với ID: " + id));
    }
}