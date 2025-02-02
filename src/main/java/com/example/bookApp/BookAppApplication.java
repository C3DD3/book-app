package com.example.bookApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookAppApplication.class, args);
		System.out.println("📚 BookApp is running on http://localhost:8080/");
	}
}
