package org.example.demo_paging.controllers;

import lombok.RequiredArgsConstructor;
import org.example.demo_paging.models.entities.Customer;
import org.example.demo_paging.models.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public Page<Customer> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return customerService.getCustomer(page, size);
    }
}
