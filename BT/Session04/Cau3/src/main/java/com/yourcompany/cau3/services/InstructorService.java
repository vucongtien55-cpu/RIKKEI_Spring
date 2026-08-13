package com.yourcompany.cau3.services;

import com.yourcompany.cau3.entities.Instructor;
import com.yourcompany.cau3.repositoris.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    //Tim giảng viên theo id
    public Instructor findInstructorById(Long id){
        return instructorRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Giảng viên không tồn tại với id: " + id));
    }

    //Lấy danh sách giảng viên
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    //Thêm giảng viên
    public Instructor addInstructor(Instructor instructor) {
        Instructor instructor1 = new Instructor();
        instructor1.setName(instructor.getName());
        instructor1.setEmail(instructor.getEmail());

        return instructorRepository.save(instructor1);
    }
}

