package org.example.ridehailing.ridehailingmanagement.repositories;

import org.example.ridehailing.ridehailingmanagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);
}