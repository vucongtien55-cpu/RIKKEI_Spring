package org.example.demo_query.services.Impl;

import lombok.RequiredArgsConstructor;
import org.example.demo_query.dtos.reponse.BookRep;
import org.example.demo_query.dtos.request.BookReq;
import org.example.demo_query.entities.Book;
import org.example.demo_query.repositories.IBookRepository;
import org.example.demo_query.services.IBookService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final IBookRepository bookRepository;
    //Hàm chuyển đổi Book -> Response
    private BookRep toReponse(Book book){
        if(book == null){
            return null;
        }
        return BookRep.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getCategory())
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .publistYear(book.getPublishYear())
                .build();
    }
    //Hàm chuyển đổi Request -> Entity
    private Book toBook(BookReq bookReq){
        if(bookReq == null) return null;
        return Book.builder()
                .title(bookReq.getTitle())
                .author(bookReq.getAuthor())
                .category(bookReq.getCategory())
                .price(bookReq.getPrice())
                .quantity(bookReq.getQuantity())
                .publishYear(bookReq.getPublistYear())
                .build();
    }

    @Override
    public List<BookRep> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(book -> this.toReponse(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookRep findById(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh sách theo id: "+ id));
        return toReponse(book);
    }

    @Override
    public BookRep create(BookReq bookReq) {
        Book book = toBook(bookReq);
        Book saveBook = bookRepository.save(book);
        return toReponse(saveBook);
    }

    @Override
    public BookRep update(BookReq bookReq, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy sách có id:"+ id ));
        //Cập nhật dữ liệu mới
        book.setTitle(bookReq.getTitle());
        book.setAuthor(bookReq.getAuthor());
        book.setCategory(bookReq.getCategory());
        book.setPrice(bookReq.getPrice());
        book.setQuantity(bookReq.getQuantity());
        book.setPublishYear(bookReq.getPublistYear());
        Book updatBook = bookRepository.save(book);
        return toReponse(updatBook);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy sách theo id: " + id));
    }
}
