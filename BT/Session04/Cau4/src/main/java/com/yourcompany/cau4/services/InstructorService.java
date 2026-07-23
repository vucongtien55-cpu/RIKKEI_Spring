package com.yourcompany.cau4.services;

import com.yourcompany.cau4.dtos.InstructorCreateRequest;
import com.yourcompany.cau4.entities.Instructor;
import com.yourcompany.cau4.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public void createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        instructorRepository.save(instructor);
    }
}
