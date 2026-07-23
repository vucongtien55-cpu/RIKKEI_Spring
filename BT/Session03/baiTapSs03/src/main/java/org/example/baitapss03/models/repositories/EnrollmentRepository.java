package org.example.baitapss03.models.repositories;
import org.example.baitapss03.models.entities.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1001L, "Lê Văn C", 101L));
        enrollments.add(new Enrollment(1002L, "Phạm Thị D", 102L));
    }

    // 1. Lấy toàn bộ danh sách đăng ký
    public List<Enrollment> findAll() {
        return enrollments;
    }

    // 2. Tìm đăng ký theo ID
    public Enrollment findById(Long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 3. Tạo mới đăng ký
    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    // 4. Cập nhật thông tin đăng ký
    public Enrollment update(Long id, Enrollment updatedEnrollment) {
        Enrollment existing = findById(id);
        if (existing != null) {
            existing.setStudentName(updatedEnrollment.getStudentName());
            existing.setCourseId(updatedEnrollment.getCourseId());
            return existing;
        }
        return null;
    }

    // 5. Xóa đăng ký theo ID
    public Enrollment deleteById(Long id) {
        Enrollment existing = findById(id);
        if (existing != null) {
            enrollments.remove(existing);
            return existing;
        }
        return null;
    }
}
