package com.example.bookApp.service.abstracts;

import com.example.bookApp.entity.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    Optional<Author> findAuthorByNameSurname(String nameSurname);
    Author saveAuthor(Author author);

    List<Author> findAllAuthors();
}