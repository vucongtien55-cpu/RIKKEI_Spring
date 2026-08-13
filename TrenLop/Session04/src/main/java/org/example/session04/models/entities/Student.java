package org.example.session04.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="Students", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "Class_id")
    private Classes classes;

    @ManyToMany
    @JoinTable(
            name = "Student_Course",//Tên bảng trung gian
            joinColumns = @JoinColumn(name = "Student_id"),//Khóa ngoại trả về chính nó
            inverseJoinColumns = @JoinColumn(name = "Course_id")//Khóa ngoại trỏ veef phía đối diện
    )
    private List<Course> course;
}
