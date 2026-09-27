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
@Getter
@Setter
@Table(name = "Người dùng")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "Địa chỉ")
    private String address;
    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "UserProfile_id")
    private UserProfile userProfile;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;
}
