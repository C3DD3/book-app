package com.example.bookApp.client;

import com.example.bookApp.client.models.GoogleBooksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleBooksClient", url = "${google.books.api.url}")
public interface GoogleBooksClient {

    @GetMapping
    GoogleBooksResponse searchBooks(@RequestParam("q") String query);
}
