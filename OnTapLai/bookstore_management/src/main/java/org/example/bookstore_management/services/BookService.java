package org.example.bookstore_management.services;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.Book;
import org.example.bookstore_management.repositories.BookRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //Lấy tất cả các danh sách
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    //Lấy chi tieiets 1 cuốn sách theo ID(Trả về lỗi nếu không tìm thấy)
    public Book getById(long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy sách có ID là: "+id));
    }

    //Tìm danh sách theo tên tác giả
    public List<Book> getByAuthor(String Author){
        return bookRepository.findByAuthor(Author);
    }

    //Tìm sách theo 1 danh mục
    public List<Book> getByCategoryId(Long id){
        return bookRepository.findByid(id);
    }

}
