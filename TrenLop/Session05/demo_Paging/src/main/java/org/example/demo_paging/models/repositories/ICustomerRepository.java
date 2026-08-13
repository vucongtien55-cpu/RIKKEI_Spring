package org.example.demo_paging.models.repositories;

import lombok.RequiredArgsConstructor;
import org.example.demo_paging.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
