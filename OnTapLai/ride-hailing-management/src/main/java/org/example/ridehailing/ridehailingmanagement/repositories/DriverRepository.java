package org.example.ridehailing.ridehailingmanagement.repositories;

import org.example.ridehailing.ridehailingmanagement.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Boolean existsByName(String name);
}
