package org.example.ridehailing.ridehailingmanagement.services;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Vehicle;
import org.example.ridehailing.ridehailingmanagement.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe theo ID: " + id));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new RuntimeException("Xe " + vehicle.getLicensePlate() + " đã tồn tại.");
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Xe có id: " + id + " không tồn tại."));

        if (vehicle.getLicensePlate() != null
                && !existingVehicle.getLicensePlate().equals(vehicle.getLicensePlate())
                && vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new RuntimeException("Xe " + vehicle.getLicensePlate() + " đã tồn tại.");
        }

        existingVehicle.setLicensePlate(vehicle.getLicensePlate());
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setColor(vehicle.getColor());
        existingVehicle.setSeatCapacity(vehicle.getSeatCapacity());

        return vehicleRepository.save(existingVehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe có id: " + id));
        vehicleRepository.delete(vehicle);
    }
}