package org.example.quanlykhoahoc.repositories;

import org.example.quanlykhoahoc.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    // Tìm người dùng theo username (phục vụ login)
    Optional<Users> findByUsername(String username);
    List<Users> findAllByIsActive(Boolean isActive);
}