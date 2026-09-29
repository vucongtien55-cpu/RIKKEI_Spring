package org.example.ridehailing.ridehailingmanagement.services;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Driver;
import org.example.ridehailing.ridehailingmanagement.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    public Driver getById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế theo ID: " + id));
    }

    public Driver createDriver(Driver driver) {
        if (driverRepository.existsByName(driver.getName())) {
            throw new RuntimeException("Tài xế " + driver.getName() + " đã tồn tại.");
        }
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver driver) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tài xế có id: " + id + " không tồn tại."));

        if (driver.getName() != null
                && !existingDriver.getName().equals(driver.getName())
                && driverRepository.existsByName(driver.getName())) {
            throw new RuntimeException("Tài xế " + driver.getName() + " đã tồn tại.");
        }

        existingDriver.setName(driver.getName());
        existingDriver.setEmail(driver.getEmail());
        existingDriver.setPhone(driver.getPhone());
        existingDriver.setLicenseNumber(driver.getLicenseNumber());
        existingDriver.setStatus(driver.getStatus());
        existingDriver.setVehicle(driver.getVehicle());

        return driverRepository.save(existingDriver);
    }

    public void deleteDriver(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế có id: " + id));
        driverRepository.delete(driver);
    }
}