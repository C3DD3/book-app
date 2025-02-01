package com.example.bookApp.service.concretes;

import com.example.bookApp.entity.Book;
import com.example.bookApp.entity.Publisher;
import com.example.bookApp.repository.PublisherRepository;
import com.example.bookApp.service.abstracts.IPublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements IPublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Optional<Publisher> findPublisherByName(String name) {
        return publisherRepository.findByName(name);
    }

    @Override
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Book> findBookRandom2Publisher() {
        return publisherRepository.findBookRandom2Publisher();
    }

}