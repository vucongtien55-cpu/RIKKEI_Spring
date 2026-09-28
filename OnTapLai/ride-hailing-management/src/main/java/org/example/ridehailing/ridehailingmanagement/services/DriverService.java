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

    //Lấy tất cả thông tin tài xế
    public List<Driver> getAll(){
        return driverRepository.findAll();
    }

    //Laasy thông tin tài xế theo id
    public Driver getById(Long id){
        return driverRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy tài xế theo ID: " + id));
    }

    //Thêm tài xế mới đồng thời lưu thong tin
    public Driver createDriver(Driver driver){
        if(driverRepository.exisByName(driver.getName())){
            throw new RuntimeException("Tài xế " + driver.getName() + " đã tồn tại.");
        }
        return driverRepository.save(driver);
    }

    //Cập nhật thông tin của tài xế
    public Driver updateDriver(Long id, Driver driver){
        Driver driver1 = driverRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tài xế có id: " + id + " không ton tại."));
        if(!driver1.getName().equals(driver.getName())&& driverRepository.exisByName(driver.getName())){
            throw new RuntimeException("Tài xế " + driver.getName() + " đã tồn tại.");
        }
        driver1.setName(driver.getName());
        driver1.setEmail(driver.getEmail());
        driver1.setPhone(driver.getPhone());
        driver1.setLicenseNumber(driver.getLicenseNumber());
        driver1.setStatus(driver.getStatus());
        return driverRepository.save(driver);
    }

    //Xóa thông tin tài xế
    public void deleteDriver(Long id){
        Driver driver = driverRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thay tài xế có id: " + id));
        driverRepository.delete(driver);
    }
}
