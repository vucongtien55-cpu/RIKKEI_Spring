package org.example.session05.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.session05.models.entities.Customer;
import org.example.session05.models.services.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(name ="search" , defaultValue = "") String search
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                customerService.findAllByNameAndEmail(search)
        );
    }

    //API update trạng thái
    @PatchMapping("/id")
    public ResponseEntity<?> updateStatus(Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
          customerService.updateStatus(id)
        );
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> findAllPagination(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size
    ){
        Pageable pageable = PageRequest.of(page , size);
        Page<Customer> customers = customerService.findAllAndPagination(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> findAllPagination(
            @RequestParam(name = "page", defaultValue = "0") int page
//            @RequestParam(name = "size", defaultValue = "2") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Slice<Customer> customers = customerService.findAllWithSlice(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

}
