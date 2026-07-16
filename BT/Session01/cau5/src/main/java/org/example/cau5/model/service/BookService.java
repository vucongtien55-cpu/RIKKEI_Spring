package org.example.cau5.model.service;

import org.example.cau5.model.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private static final List<Book> danhSach = new ArrayList<>(List.of(
            new Book(1L , "Java", "Quang Văn Trường", 30),
            new Book(2L, "C#", "Ngô Việt Anh", 45),
            new Book(3L, "Python", "Nguyễn Văn Sơn", 25)
    ));

    public static List<Book> getDanhSach(){
        return danhSach;
    }

    public static Book addBook(Book newBook){
        Long nextId = (Long) (danhSach.size() + 1L);

        newBook.setId(nextId);

        danhSach.add(newBook);

        return newBook;
    }

    public static Book updateBook(Long id, Book updateBook){
        for(Book b : danhSach){
            if(b.getId().equals(id)){
                b.setTitle(updateBook.getTitle());
                b.setAuthor(updateBook.getAuthor());
                b.setQuantity(updateBook.getQuantity());
                return b;
            }
        }
        return null;
    }



    public static boolean deleteBook(Long id){
        danhSach.removeIf(b -> b.getId().equals(id));
        return false;
    }
}
