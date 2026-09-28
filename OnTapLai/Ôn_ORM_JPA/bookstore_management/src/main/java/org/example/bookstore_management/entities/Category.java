package org.example.bookstore_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Danh mục sách")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Tên sách")
    private String name;
    @Column(name = "Miêu tả sách")
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Book> books;
}
