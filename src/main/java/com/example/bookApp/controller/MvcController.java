package com.example.bookApp.controller;

import com.example.bookApp.entity.Author;
import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;
import com.example.bookApp.service.concretes.AuthorServiceImpl;
import com.example.bookApp.service.concretes.BookServiceImpl;
import com.example.bookApp.service.concretes.PublisherServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MvcController {
    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final PublisherServiceImpl publisherService;

    public MvcController(BookServiceImpl bookService, AuthorServiceImpl authorService, PublisherServiceImpl publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public String index(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Optional<Book> bookOpt = bookService.findBookById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            model.addAttribute("book", book);
            model.addAttribute("publisherName", book.getPublisher().getName());
            model.addAttribute("authorName", book.getAuthor().getNameSurname());
        }
        return "addBook";
    }

    @GetMapping("/publishedAfter/{year}")
    public ResponseEntity<List<Book>> getBooksPublishedAfter(@PathVariable int year) {
        return ResponseEntity.ok(bookService.getBooksPublishedAfter(year));
    }

    @PostMapping("/saveBook")
    public String saveBook(
            @ModelAttribute("book") Book book,
            @RequestParam("publisherName") String publisherName,
            @RequestParam("authorName") String authorName,
            BindingResult result,
            Model model
    ) {
        Optional<Publisher> existingPublisher = publisherService.findPublisherByName(publisherName);
        Publisher publisher = existingPublisher.orElseGet(() -> publisherService.savePublisher(new Publisher(publisherName)));

        Optional<Author> existingAuthor = authorService.findAuthorByNameSurname(authorName);
        Author author = existingAuthor.orElseGet(() -> authorService.saveAuthor(new Author(authorName)));

        book.setPublisher(publisher);
        book.setAuthor(author);
        bookService.saveBook(book);

        return "redirect:/";
    }

    @GetMapping("/listPublishers")
    public String listAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.findAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    @GetMapping("/listBooks")
    public String listAllBooks(Model model) {
        List<String> books = bookService.findBookJustName();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/listAuthors")
    public String listAllAuthors(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/booksWithAuthorsForTwoPublishers")
    public String getBooksWithAuthorsForTwoPublishers(Model model) {
        List<Book> books = publisherService.findBookRandom2Publisher(); // Book nesnelerinin döndüğünden emin olun
        model.addAttribute("books", books);
        return "booksWithAuthorsForTwoPublishers";
    }

}
