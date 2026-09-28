package org.example.ridehailing.ridehailingmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Tài_Xế")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Tên")
    private String name;
    @Column(name = "Số điện thoại")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Số bằng lái xe")
    private String licenseNumber;
    @Column(name = "Trạng thái")
    private String status;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany
    private List<Driver> driver;
}

