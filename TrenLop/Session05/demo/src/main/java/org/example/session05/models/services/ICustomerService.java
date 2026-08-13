package org.example.session05.models.services;

import org.example.session05.models.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAllByNameAndEmail(String search);
    Customer updateStatus(Long id);

    Page<Customer> findAllAndPagination(Pageable pageable);

    Slice<Customer> findAllWithSlice(Pageable pageable);
}
