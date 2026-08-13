package org.example.demo_query.repositories;

import org.example.demo_query.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> id(Long id);
}
