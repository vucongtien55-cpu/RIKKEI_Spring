package org.example.session05.models.services.Impl;

import lombok.RequiredArgsConstructor;
import org.example.session05.models.entities.Customer;
import org.example.session05.models.repositories.ICustomerRepository;
import org.example.session05.models.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAllByNameAndEmail(String search) {
        if(search.isEmpty()){
            return customerRepository.findAll();
        }
        return customerRepository.searchNameOrEmailContains(search);
    }

    @Override
    public Customer updateStatus(Long id) {
        customerRepository.updateStatus(id);
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + "does not exits"));
    }

    @Override
    public Page<Customer> findAllAndPagination(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Slice<Customer> findAllWithSlice(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
