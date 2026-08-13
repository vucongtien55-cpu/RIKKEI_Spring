package org.example.demo_query.controllers;

import lombok.RequiredArgsConstructor;
import org.example.demo_query.dtos.reponse.BookRep;
import org.example.demo_query.dtos.request.BookReq;
import org.example.demo_query.entities.Book;
import org.example.demo_query.services.Impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<BookRep>> getAllBook(){
        List<BookRep> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRep> getAllId(@PathVariable Long id){
        BookRep book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookRep> createBook(@PathVariable Long id, @RequestBody BookReq bookReq){
        BookRep bookRep = bookService.create(bookReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRep);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRep> updateBook(@PathVariable Long id, @RequestBody BookReq bookReq){
        BookRep updateBook = bookService.update(bookReq, id);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookRep> deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
