package org.example.baitapss03.models.services;
import org.example.baitapss03.models.entities.Course;
import org.example.baitapss03.models.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.create(course);
    }

    public Course updateCourse(Long id, Course course) {
        return courseRepository.update(id, course);
    }

    public Course deleteCourseById(Long id) {
        return courseRepository.deleteById(id);
    }
}
