package com.example.bookApp.service.concretes;

import com.example.bookApp.entity.Author;
import com.example.bookApp.entity.Publisher;
import com.example.bookApp.repository.AuthorRepository;
import com.example.bookApp.service.abstracts.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findAuthorByNameSurname(String nameSurname) {
        return authorRepository.findByNameSurname(nameSurname);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
}
