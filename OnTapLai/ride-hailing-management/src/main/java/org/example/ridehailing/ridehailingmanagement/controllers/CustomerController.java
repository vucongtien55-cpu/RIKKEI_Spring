package org.example.ridehailing.ridehailingmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Customer;
import org.example.ridehailing.ridehailingmanagement.services.CustomerSevice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerSevice customerSevice;

    // Xem tất cả
    @GetMapping
    public List<Customer> getAll() {
        return customerSevice.getAll();
    }

    // Lấy theo id
    @GetMapping("{id}")
    public Customer getById(@PathVariable Long id) {
        return customerSevice.getById(id);
    }

    // Thêm mới
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerSevice.createCustomer(customer);
    }

    // Cập nhật
    @PutMapping("{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerSevice.updateCustomer(id, customer);
    }

    // Xóa
    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerSevice.deleteCustomer(id);
    }
}