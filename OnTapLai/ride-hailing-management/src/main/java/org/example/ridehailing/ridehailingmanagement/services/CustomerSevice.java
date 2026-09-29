package org.example.ridehailing.ridehailingmanagement.services;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Customer;
import org.example.ridehailing.ridehailingmanagement.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerSevice {
    private final CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng theo ID: " + id));
    }

    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Khách hàng " + customer.getEmail() + " đã tồn tại.");
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng có id: " + id));

        if (customer.getEmail() != null
                && !existingCustomer.getEmail().equals(customer.getEmail())
                && customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Khách hàng " + customer.getEmail() + " đã tồn tại.");
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng có id: " + id));
        customerRepository.deleteById(id);
    }
}