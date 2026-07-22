package org.example.baitapss03.models.repositories;

import org.example.baitapss03.models.entities.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructor(1L, "Nguyễn Văn A", "nguyenvana@example.com"));
        instructors.add(new Instructor(2L, "Trần Thị B", "tranthib@example.com"));
    }

    // 1. Lấy toàn bộ danh sách
    public List<Instructor> findAll() {
        return instructors;
    }

    // 2. Tìm giảng viên theo ID
    public Instructor findById(Long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 3. Thêm mới giảng viên
    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    // 4. Cập nhật thông tin giảng viên
    public Instructor update(Long id, Instructor updatedInstructor) {
        Instructor existing = findById(id);
        if (existing != null) {
            existing.setName(updatedInstructor.getName());
            existing.setEmail(updatedInstructor.getEmail());
            return existing;
        }
        return null;
    }

    // 5. Xóa giảng viên theo ID
    public Instructor deleteById(Long id) {
        Instructor existing = findById(id);
        if (existing != null) {
            instructors.remove(existing);
            return existing;
        }
        return null;
    }
}
