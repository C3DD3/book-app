package com.example.bookApp.controller;

import com.example.bookApp.client.models.GoogleBooksResponse;
import com.example.bookApp.entity.Author;
import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;
import com.example.bookApp.entity.dtos.BookSearchResponseDto;
import com.example.bookApp.mapper.GoogleBookMapperService;
import com.example.bookApp.service.abstracts.IAuthorService;
import com.example.bookApp.service.abstracts.IBookService;
import com.example.bookApp.service.abstracts.IPublisherService;
import com.example.bookApp.service.concretes.BookSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class BookController {
    private final IBookService bookService;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;
    private final BookSearchService bookSearchService;
    private final GoogleBookMapperService mapper;

    public BookController(IBookService bookService, IAuthorService authorService, IPublisherService publisherService, BookSearchService bookSearchService, GoogleBookMapperService mapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookSearchService = bookSearchService;
        this.mapper = mapper;
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
    @PostMapping("/saveBook")
    public String saveBook(
            @ModelAttribute("book") Book book,
            @RequestParam("publisherName") String publisherName,
            @RequestParam("authorName") String authorName,
            BindingResult result,
            Model model
    ) {
        if (book.getPublishedDate().isAfter(LocalDate.now())) {
            result.rejectValue("publishedDate", "error.book", "Published date cannot be in the future!");
        }
        if (result.hasErrors()) {
            model.addAttribute("publisherName", publisherName);
            model.addAttribute("authorName", authorName);
            return "addBook";
        }

        Optional<Publisher> existingPublisher = publisherService.findPublisherByName(publisherName);
        Publisher publisher = existingPublisher.orElseGet(() -> publisherService.savePublisher(new Publisher(publisherName)));

        Optional<Author> existingAuthor = authorService.findAuthorByNameSurname(authorName);
        Author author = existingAuthor.orElseGet(() -> authorService.saveAuthor(new Author(authorName)));

        book.setPublisher(publisher);
        book.setAuthor(author);
        bookService.saveBook(book);

        return "redirect:/";
    }
    @GetMapping("/searchBook")
    public String showSearchBookForm() {
        return "searchBook";
    }

    @GetMapping("/searchBookResults")
    public String searchBookResults(@RequestParam("title") String title, Model model) {
        GoogleBooksResponse response = bookSearchService.searchBooksByTitle(title);

        if (response == null || response.getTotalItems() == 0 || response.getItems() == null || response.getItems().isEmpty()) {
            model.addAttribute("errorMessage", "Aradığınız kitap bulunamadı.");
            return "searchBook";
        }

        model.addAttribute("booksResponse", response);
        return "searchBookResults";
    }

    @GetMapping(value = "/api/searchBookResults", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<BookSearchResponseDto>> searchBookResultsJson(@RequestParam("title") String title) {
        GoogleBooksResponse response = bookSearchService.searchBooksByTitle(title);
        ArrayList<BookSearchResponseDto> responseDto = (ArrayList<BookSearchResponseDto>) BookSearchResponseDto.fromList(response.getItems(), mapper);
        return ResponseEntity.ok(responseDto);
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
        List<Book> books = publisherService.findBookRandom2Publisher();
        model.addAttribute("books", books);
        return "booksWithAuthorsForTwoPublishers";
    }
    @GetMapping("/after_2023")
    public String listPublishedAfter2023(Model model) {
        List<Book> books = bookService.getBooksPublishedAfter(2023);
        model.addAttribute("books", books);
        return "after_2023";
    }
    @GetMapping("/booksStartingWithA")
    public String getBooksStartingWithA(Model model) {
        List<Book> books = bookService.getBooksStartingWithA();
        model.addAttribute("books", books);
        return "booksStartingWithA";
    }

}
