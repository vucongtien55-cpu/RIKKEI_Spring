package org.example.session01.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Students {
    private int id;
    private String name;
    private int age;
    private String email;
    private String address;
}
