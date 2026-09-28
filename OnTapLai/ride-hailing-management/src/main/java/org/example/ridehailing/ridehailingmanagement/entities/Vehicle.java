package org.example.ridehailing.ridehailingmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Xe")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Biển số xe")
    private String licensePlate;
    @Column(name = "Hãng xe")
    private String brand;
    @Column(name = "Dòng xe")
    private String model;
    @Column(name = "Màu xe")
    private String color;
    @Column(name = "Số chỗ")
    private Integer seatCapacity;
//    @Column(name = "Xe thuộc tài xế nào")
//    private Long driverId;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;
}
