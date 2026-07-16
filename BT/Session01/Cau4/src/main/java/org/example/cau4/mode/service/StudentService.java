package org.example.cau4.mode.service;

import org.example.cau4.mode.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static final List<Student> danhSach = new ArrayList<>(List.of(
            new Student(1L, "Nguyễn Văn Nhật", 20, "OTO"),
            new Student(2L, "Vũ Công Tiến", 20, "CNTT"),
            new Student(3L, "Từ Xuân Đạt", 20, "Marketing")
    ));

    //Hiển thị danh sách
    public static List<Student> getDanhSach() {
        return danhSach;
    }
    // theem danh sách
    public static Student addStudent(Student newStudent){
        Long nextId = (Long) (danhSach.size() + 1L);
        newStudent.setId(nextId);
        danhSach.add(newStudent);
        return newStudent;
    }
    //sửa danh sách
    public static Student updateStudent(Long id, Student updateStudent){
        for(Student sv : danhSach){
            if(sv.getId().equals(id)){
                sv.setName(updateStudent.getName());
                sv.setMajor(updateStudent.getMajor());
                sv.setAge(updateStudent.getAge());
                return sv;
            }
        }
        return null;
    }

    //xóa danh sách
    public static boolean deleteStudent(Long id){
        return danhSach.removeIf(sv -> sv.getId().equals(id));
    }
}
