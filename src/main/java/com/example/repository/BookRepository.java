package com.example.repository;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE 'A%'")
    List<Book> findBooksStartingWithA();

    @Query("SELECT b FROM Book b WHERE EXTRACT(YEAR FROM b.publishedDate) > :year")
    List<Book> findBooksPublishedAfter(@Param("year") int year);
}
