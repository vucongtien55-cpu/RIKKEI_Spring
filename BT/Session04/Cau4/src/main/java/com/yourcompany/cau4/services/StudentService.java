package com.yourcompany.cau4.services;

import com.yourcompany.cau4.dtos.StudentCreateRequest;
import com.yourcompany.cau4.entities.Student;
import com.yourcompany.cau4.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void createStudent(StudentCreateRequest req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        studentRepository.save(student);
    }
}
