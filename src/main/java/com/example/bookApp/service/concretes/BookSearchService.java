package com.example.bookApp.service.concretes;

import com.example.bookApp.client.GoogleBooksClient;
import com.example.bookApp.client.models.GoogleBooksResponse;
import org.springframework.stereotype.Service;

@Service
public class BookSearchService {

    private final GoogleBooksClient googleBooksClient;

    public BookSearchService(GoogleBooksClient googleBooksClient) {
        this.googleBooksClient = googleBooksClient;
    }

    public GoogleBooksResponse searchBooksByTitle(String title) {
        return googleBooksClient.searchBooks(title);
    }
}
