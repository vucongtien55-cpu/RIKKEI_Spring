package org.example.bookstore_management.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.Book;
import org.example.bookstore_management.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAll(){
        return bookService.getAllBook();
    }

    //Lấy danh sách theo id
    @GetMapping("/{id}")
    public Book getById(@PathVariable long id){
        return bookService.getById(id);
    }

    //Tìm kiếm theo tên tác giả
    @GetMapping("/author")
    public List<Book> getByAuthor(@RequestParam String author){
        return bookService.getByAuthor(author);
    }

    // lấy danh sách thuộc 1 danh mục
    @GetMapping("/category/{categogy_id}")
    public List<Book> getByCategoryId(@PathVariable Long categogy_id){
        return bookService.getByCategoryId(categogy_id);
    }
}
