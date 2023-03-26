package com.hh.bookstore.sof23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hh.bookstore.sof23.web.BookController;
import com.hh.bookstore.sof23.web.CategoryController;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class ControllerTests {

	@Autowired
	private BookController bookcontroller;
	
	
	@Test
	public void bookControllerLoads() throws Exception {
		assertThat(bookcontroller).isNotNull();
	}
	
	@Autowired
	private CategoryController categorycontroller;
	
	@Test
	public void categoryControllerLoads() throws Exception {
		assertThat(categorycontroller).isNotNull();
	}
}
