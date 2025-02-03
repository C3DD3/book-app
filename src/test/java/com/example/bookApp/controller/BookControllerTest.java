//deleteBookById() metodu için UnitTest

package com.example.bookApp.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.bookApp.repository.BookRepository;
import com.example.bookApp.service.concretes.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteBook_shouldInvokeRepositoryDeleteById() {
        // Given
        doNothing().when(bookRepository).deleteById(anyLong());

        // When
        bookService.deleteBookById(1L);

        // Then
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
