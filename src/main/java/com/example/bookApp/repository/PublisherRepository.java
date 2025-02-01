package com.example.bookApp.repository;


import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByName(String name);

    @Query("SELECT b1 FROM Publisher p1 " +
            "JOIN p1.books b1 " +
            "JOIN b1.author a1 " +
            "WHERE p1.id IN (SELECT p2.id FROM Publisher p2 ORDER BY RANDOM() LIMIT 2)")
    List<Book> findBookRandom2Publisher();

}
