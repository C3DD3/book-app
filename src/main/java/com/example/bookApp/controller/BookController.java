package com.example.bookApp.controller;

import com.example.bookApp.entity.Book;
import com.example.bookApp.service.concretes.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/startswithA")
    public ResponseEntity<List<Book>> getBooksStartingWithA() {
        return ResponseEntity.ok(bookService.getBooksStartingWithA());
    }

    @GetMapping("/publishedAfter/{year}")
    public ResponseEntity<List<Book>> getBooksPublishedAfter(@PathVariable int year) {
        return ResponseEntity.ok(bookService.getBooksPublishedAfter(year));
    }
}
