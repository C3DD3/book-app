package com.example.bookApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookAppApplication.class, args);
		System.out.println("📚 BookApp is running on http://localhost:8080/");
	}
}
