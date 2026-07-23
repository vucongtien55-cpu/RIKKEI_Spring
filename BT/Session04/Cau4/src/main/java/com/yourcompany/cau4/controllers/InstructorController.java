package com.yourcompany.cau4.controllers;

import com.yourcompany.cau4.dtos.ApiResponse;
import com.yourcompany.cau4.dtos.InstructorCreateRequest;
import com.yourcompany.cau4.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // POST /instructors
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInstructor(@RequestBody InstructorCreateRequest req) {
        instructorService.createInstructor(req);
        return ResponseEntity.ok(ApiResponse.success("Tạo giảng viên thành công!"));
    }
}
