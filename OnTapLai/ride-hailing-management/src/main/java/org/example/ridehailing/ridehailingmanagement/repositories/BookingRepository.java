package org.example.ridehailing.ridehailingmanagement.repositories;

import org.example.ridehailing.ridehailingmanagement.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Long id(Long id);
}
