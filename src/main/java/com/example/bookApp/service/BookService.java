package com.example.bookApp.service;

import com.example.bookApp.entity.Book;
import com.example.bookApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooksStartingWithA() {
        return bookRepository.findBooksStartingWithA();
    }

    public List<Book> getBooksPublishedAfter(int year) {
        return bookRepository.findBooksPublishedAfter(year);
    }
}
