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
@Table(name = "Khách_hàng")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "So điện thoại")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "Địa chỉ")
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Booking> booking;
}
