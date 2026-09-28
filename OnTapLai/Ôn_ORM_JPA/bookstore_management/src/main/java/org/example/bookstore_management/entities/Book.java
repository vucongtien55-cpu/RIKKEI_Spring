package org.example.bookstore_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sách")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Tiêu đề")
    private String title;
    @Column(name = "Tác giả")
    private String author;
    @Column(name = "Giá")
    private Double price;
    @Column(name = "Số lượng tồn kho")
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

    @ManyToMany(mappedBy = "books")
    List<User> users;
}
