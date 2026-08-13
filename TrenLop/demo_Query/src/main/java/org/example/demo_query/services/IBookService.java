package org.example.demo_query.services;

import org.example.demo_query.dtos.reponse.BookRep;
import org.example.demo_query.dtos.request.BookReq;
import org.example.demo_query.entities.Book;

import java.util.List;

public interface IBookService {
     List<BookRep> findAll();
     BookRep findById(Long id);
     BookRep create(BookReq bookReq);
     BookRep update(BookReq bookReq, Long id);
     void delete(Long id);
}
