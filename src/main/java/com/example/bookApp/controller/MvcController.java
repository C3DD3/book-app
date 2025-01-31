package com.example.bookApp.controller;

import com.example.bookApp.entity.Author;
import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;
import com.example.bookApp.repository.AuthorRepository;
import com.example.bookApp.repository.BookRepository;
import com.example.bookApp.repository.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MvcController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public MvcController(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook"; // HTML sayfa adı
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteBook(@PathVariable Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/"; // HTML sayfa adı
    }
    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.getBookById(id); // Kitabı ID ile bul
        model.addAttribute("book", book); // Kitabı model'e ekle
        model.addAttribute("publisherName", book.getPublisher().getName()); // Kitabı model'e ekle
        model.addAttribute("authorName", book.getAuthor().getNameSurname()); // Kitabı model'e ekle

        return "addBook"; // addBook.html sayfasına yönlendir
    }

    @PostMapping("/saveBook")
    public String saveBook(
            @ModelAttribute("book") Book book,
            @RequestParam("publisherName") String publisherName,
            @RequestParam("authorName") String authorName,
            BindingResult result,
            Model model
    ) {
        // Publisher kontrolü: varsa getir, yoksa oluştur
        Optional<Publisher> existingPublisher = publisherRepository.findByName(publisherName);
        Publisher publisher = existingPublisher.orElseGet(() -> {
            Publisher newPublisher = new Publisher(publisherName);
            return publisherRepository.save(newPublisher);
        });

        // Author kontrolü: varsa getir, yoksa oluştur
        Optional<Author> existingAuthor = authorRepository.findByNameSurname(authorName);
        Author author = existingAuthor.orElseGet(() -> {
            Author newAuthor = new Author(authorName);
            return authorRepository.save(newAuthor);
        });

        // Kitaba atama yap ve kaydet
        book.setPublisher(publisher);
        book.setAuthor(author);
        bookRepository.save(book);

        return "redirect:/";
    }
}