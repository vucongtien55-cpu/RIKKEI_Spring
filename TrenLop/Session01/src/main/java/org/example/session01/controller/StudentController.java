package org.example.session01.controller;

import org.example.session01.entity.Students;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @GetMapping
    public Students getStudent() {
        return new Students(1, "Vu Cong Tien", 20, "vucongtien55@gmail.com", "Ha Noi");
    }
}
