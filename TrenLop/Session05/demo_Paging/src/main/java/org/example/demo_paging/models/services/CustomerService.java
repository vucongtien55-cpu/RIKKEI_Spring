package org.example.demo_paging.models.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.demo_paging.models.entities.Customer;
import org.example.demo_paging.models.repositories.ICustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ICustomerRepository customerRepository;

    //Tạo pageable
    public Page<Customer> getCustomer(int page, int size){
        Pageable pageable = PageRequest.of(
                page, size
        );
        //Lấy dữ liệu
        return customerRepository.findAll(pageable);
    }
}
