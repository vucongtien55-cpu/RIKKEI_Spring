package org.example.ridehailing.ridehailingmanagement.repositories;

import org.example.ridehailing.ridehailingmanagement.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {
    Boolean existsByLicensePlate(String licensePlate);
}
