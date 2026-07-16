package org.example.cau4.controller;

import org.example.cau4.mode.entity.Student;
import org.example.cau4.mode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getDanhSach(){
        return StudentService.getDanhSach();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student newStudent){
        return StudentService.addStudent(newStudent);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id , @RequestBody Student updateStudent){
        return StudentService.updateStudent(id, updateStudent);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        boolean isDelete = StudentService.deleteStudent(id);
        if(isDelete){
            return "Xóa thành công";
        } else{
            return "Không tìm thấy id: " + id;
        }
    }
}
