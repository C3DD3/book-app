package com.example.bookApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "isbn_13")
    private String ISBN13;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Get the publish year from the publishedDate
    public int getPublishYear() {
        return publishedDate != null ? publishedDate.getYear() : 0;
    }
}
