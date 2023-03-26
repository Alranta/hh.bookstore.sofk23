package com.hh.bookstore.sof23;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hh.bookstore.sof23.domain.Book;
import com.hh.bookstore.sof23.domain.BookRepository;
import com.hh.bookstore.sof23.domain.Category;
import com.hh.bookstore.sof23.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest

public class JpaRepositoryTests {
	
	Book book;

	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private CategoryRepository categoryrepository;

	@Test
	public void findByTitleHasAuthorTest() {
		List<Book> books = bookrepository.findByTitle("Aapinen");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Mikael Agricola");
	}
	
	@Test
	public void findAllCategories() {
		List<Category> categories = (List<Category>) categoryrepository.findAll();
		assertThat(categories).isNotNull();
		assertThat(categories).hasSize(3);
	}
	
	@Test
	public void newBookTest() {
		Book newBook = new Book("Elämä: Esipuhe", "Miki Liukkonen", 2021, "100999", 10, categoryrepository.findByName("Jännitys").get(0));
		bookrepository.save(newBook);
		assertThat(newBook.getId()).isNotNull();
	}
	
	@Test
	public void editBookTest() {
		Book newBook = new Book("Elämä: Esipuhe", "Miki Liukkonen", 2021, "100999", 10, categoryrepository.findByName("Jännitys").get(0));
		bookrepository.save(newBook);
		String newTitle = "Sadan vuoden yksinäisyys";
		newBook.setTitle(newTitle);
		
		Book updatedBook = (Book) bookrepository.findByTitle(newTitle);
		
		assertThat(updatedBook.getTitle()).isEqualTo(newBook);
		
	}
	
}
