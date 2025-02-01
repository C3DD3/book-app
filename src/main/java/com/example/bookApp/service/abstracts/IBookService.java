package com.example.bookApp.service.abstracts;

import com.example.bookApp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAllBooks();
    Optional<Book> findBookById(Long id);
    List<String> findBookJustName();
    Book saveBook(Book book);
    void deleteBookById(Long id);
    List<Book> getBooksPublishedAfter(int year);
    List<Book> getAllBooks(); // Tüm kitapları getir
    List<String> getAllBooksNames();
    List<Book> getBooksStartingWithA(); // 'A' ile başlayan kitapları getir

    List<Book> findBooksWithAuthorsForTwoPublishers();
}
