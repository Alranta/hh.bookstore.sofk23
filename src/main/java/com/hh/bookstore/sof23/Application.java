package com.hh.bookstore.sof23;

import java.util.Iterator;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hh.bookstore.sof23.domain.Book;
import com.hh.bookstore.sof23.domain.BookRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// CommandLineRunner ON TESTI ALUSTA H2-CONSOLEEN! 
	@Bean
	public CommandLineRunner demoDataToDatabase(BookRepository bookRepository) {
		return (arg) -> {
			Book book1 = new Book("Missä kuljimme kerran", "Kjell Westö", 2001, "100033", 12.0);
			Book book2 = new Book("Enkelipeli", "Carloz Ruiz", 2013, "1022233", 15);
			Book book3 = new Book("Aapinen", "Mikael Agricola", 0001, "0000002", 1);
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);
			
			
			List<Book> books = (List<Book>)bookRepository.findAll();
			for (Book book : books) {
				System.out.println(book.toString());
			}
		};
	}
}
