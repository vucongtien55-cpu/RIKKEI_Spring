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
@Table(name = "Đơn_đặt_xe")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Id khách hàng đặt chuyến")
    private Long customerId;
    @Column(name = "Id tài xế")
    private Long driverId;
    @Column(name = "Id xe")
    private Long vehicleId;
    @Column(name = "Điểm đón")
    private String pickupLocation;
    @Column(name = "Điểm đến")
    private String destination;
    @Column(name = "Giá")
    private double price;
    @Column(name = "Trang thái chuyến")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
