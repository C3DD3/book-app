package com.example.bookApp.service.abstracts;

import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface IPublisherService {
    Optional<Publisher> findPublisherByName(String name);
    Publisher savePublisher(Publisher publisher);

    List<Publisher> findAllPublishers();
    List<Book> findBookRandom2Publisher();
}