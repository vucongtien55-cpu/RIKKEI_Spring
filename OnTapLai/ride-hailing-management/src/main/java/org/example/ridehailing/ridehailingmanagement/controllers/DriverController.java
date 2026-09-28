package org.example.ridehailing.ridehailingmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Driver;
import org.example.ridehailing.ridehailingmanagement.services.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    //Lấy tất cả thông tin tài xế
    @GetMapping
    public List<Driver> getAll(){
        return driverService.getAll();
    }

    //Lấy thông tin tài xế theo id
    @GetMapping("{id}")
    public Driver getById(@PathVariable Long id){
        return driverService.getById(id);
    }

    //Thêm tài xế mới
    @PostMapping
    public Driver createDriver(@RequestBody Driver driver){
        return driverService.createDriver(driver);
    }

    //Cập nhật
    @PutMapping("{id}")
    public Driver updateDriver()
}
