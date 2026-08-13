package org.example.session05.models.repositories;

import jakarta.transaction.Transactional;
import org.example.session05.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByNameContainsOrEmailContains(String name, String email);
    //JPQL
    @Query("""
        select c from Customer c
                where c.name like %:seach%
                or c.email like %:seach%
        """)
    List<Customer> searchNameOrEmailContains(@Param("seach") String seach);

    @Modifying
    @Transactional
    @Query("""
       UPDATE Customer c set c.status = (NOT c.status) where c.id = :id
              """)
    void updateStatus(@Param("id") Long id);


}
