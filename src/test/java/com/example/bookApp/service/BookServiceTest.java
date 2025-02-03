//findBookById() metodu için UnitTest

package com.example.bookApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.bookApp.entity.Author;
import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;
import com.example.bookApp.repository.BookRepository;
import com.example.bookApp.service.concretes.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findBook_shouldReturnBook() {

        Publisher publisher = new Publisher("Eksen");
        Author author = new Author("J.K. Rowling");
        Book book = new Book(1L, "Harry Potter", 115d, "85546525", LocalDate.of(2028, 5, 10), publisher, author);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findBookById(1L);

        assertThat(result).isNotNull();
        assertThat(result.stream().allMatch(b -> b.getTitle().equals("Harry Potter"))).isTrue();

    }
}
