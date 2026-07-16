package org.example.cau5.controller;

import org.example.cau5.model.entity.Book;
import org.example.cau5.model.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getProduct() {
        // Gọi qua biến đối tượng BookService đã được @Autowired
        return BookService.getDanhSach();
    }

    @PostMapping
    public Book addProduct(@RequestBody Book newBook) {
        // 1. Gọi qua biến BookService (viết thường chữ p)
        // 2. Truyền đối tượng "newBook" nhận từ client vào, chứ KHÔNG dùng "new Book()" rỗng
        return BookService.addBook(newBook);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updateBook){
        return BookService.updateBook(id, updateBook);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        boolean isDelete = BookService.deleteBook(id);
        if(isDelete){
            return "Xóa thành công";
        } else {
            return "Không tìm thấy sản phẩm có id: " + id;
        }
    }

}
