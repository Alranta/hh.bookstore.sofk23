package com.hh.bookstore.sof23.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hh.bookstore.sof23.domain.Book;

@Controller
public class BookController {

	@GetMapping("/bookstore")
	
	public String showBooks(Model model) {
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(new Book("Papillon", "Henri Charriere", 1970, "22000222", 12.5));
		booklist.add(new Book("Leijat Helsingin yllä", "Kjell Westö", 1996, "22013422", 10));
		
		model.addAttribute("booklist", booklist);
		return "bookstore";
	}
}
