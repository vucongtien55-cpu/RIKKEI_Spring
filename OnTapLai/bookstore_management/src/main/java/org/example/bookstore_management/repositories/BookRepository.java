package org.example.bookstore_management.repositories;

import org.example.bookstore_management.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    //Tìm sách theo tên tác giả
    List<Book> findByAuthor(String Author);
    //Phân trang và lọc sách có giá trị lớn hơn minPrice
    List<Book> findByPriceGreaterThan(Double prive, Pageable pageable);

    //tìm kiêếm sách theo id
    List<Book> findByid(Long id);

    //Tìm kiếm danh sách theo 1 danh mục cụ thể
    List<Book> getByCategory(Long id);

}
