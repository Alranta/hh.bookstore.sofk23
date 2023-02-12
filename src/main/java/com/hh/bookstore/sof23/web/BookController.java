package com.hh.bookstore.sof23.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hh.bookstore.sof23.domain.Book;
import com.hh.bookstore.sof23.domain.BookRepository;

@Controller
public class BookController {

	
// Autowired ANNOTAATIOLLA SAADAAN KÄYTTÖÖN BOOKREPOSITORY! 
@Autowired 
private BookRepository bookRepository;
	
	@GetMapping("/bookstore")
	
	public String showBooks(Model model) {
		List<Book> booklist; // LUODAAN LISTA MIHIN KIRJAT TALLETETAAN
		model.addAttribute("books", bookRepository.findAll());
		return "bookstore";
	}
	
	// LUODAAN UUSI MAPPING UUDELLE KIRJALLE
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "bookform";
	}
	
	// LUODAAN UUSI MAPPING MILLÄ TALLENNETAAN UUSI KIRJA
	
	@PostMapping("/savebook")
	public String saveBook(@ModelAttribute Book book, Model model) {
		bookRepository.save(book);
		
		return "redirect:/bookstore";
	}
	
	// KIRJAN POISTO DeleteById(); TOIMINNOLLA
	
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:/bookstore";
	}
	
	// KIRJAN MUOKKAUS
	
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model ) {
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editform";
	}
}
