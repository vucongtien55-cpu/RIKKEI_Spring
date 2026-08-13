package com.yourcompany.cau1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "intrucstor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class Intructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameIntrucstor", length = 100, nullable = false)
    private String name;

    @Column(name = "emailIntrucstor", nullable = false)
    private String email;

}
