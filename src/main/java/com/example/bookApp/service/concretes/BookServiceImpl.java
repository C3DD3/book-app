package com.example.bookApp.service.concretes;

import com.example.bookApp.entity.Book;
import com.example.bookApp.repository.BookRepository;
import com.example.bookApp.service.abstracts.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<String> findBookJustName() {
        return bookRepository.findBookJustName();
    }
    @Override
    public Book saveBook(Book book) {return bookRepository.save(book);}
    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public List<Book> getAllBooks() { // ✅ Arayüzde tanımlanan metodu implemente ettik
        return bookRepository.findAll();
    }
    @Override
    public List<String> getAllBooksNames(){
        return bookRepository.findBookJustName();
    };

    @Override
    public List<Book> getBooksStartingWithA() {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith("A"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksWithAuthorsForTwoPublishers() {
        return null;
    }

    @Override
    public List<Book> getBooksPublishedAfter2023() {
        return bookRepository.findBooksPublishedAfter(2023);
    }

    @Override
    public List<Book> getBooksByPublisher(String publisherA) {
        return null;
    }

    @Override
    public List<Book> getBooksPublishedAfter(int year) {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getPublishedDate() != null && book.getPublishedDate().getYear() > year)
                .collect(Collectors.toList());
    }
}
