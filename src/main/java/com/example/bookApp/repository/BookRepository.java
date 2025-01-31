package com.example.bookApp.repository;

import com.example.bookApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE 'A%'")
    List<Book> findBooksStartingWithA();

    @Query("SELECT b FROM Book b WHERE FUNCTION('YEAR', b.publishedDate) > :year")
    List<Book> findBooksPublishedAfter(@Param("year") int year);

    List<Book> findByTitleStartingWith(String prefix);

    Book getBookById(Long id);
}
