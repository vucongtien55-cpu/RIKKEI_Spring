package org.example.ridehailing.ridehailingmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Vehicle;
import org.example.ridehailing.ridehailingmanagement.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Vahicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    //Xem tất cả danh sách
    @GetMapping
    public List<Vehicle> getAll(){
        return vehicleService.getAll();
    }

    //tìm theo id
    @GetMapping("{id}")
    public Vehicle getById(@PathVariable Long id){
        return vehicleService.getById(id);
    }

    //Thêm xe
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }

    //Sửa
    @PutMapping("{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle){
        return vehicleService.updateVehicle(id, vehicle);
    }

    //Xóa
    @DeleteMapping("{id}")
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }
}
