package com.hh.bookstore.sof23.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hh.bookstore.sof23.domain.Book;
import com.hh.bookstore.sof23.domain.BookRepository;
import com.hh.bookstore.sof23.domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookController {

	
// Autowired ANNOTAATIOLLA SAADAAN KÄYTTÖÖN BOOKREPOSITORY! 
@Autowired 
private BookRepository bookRepository;

// OTETAAN MYÖS KÄYTTÖÖN KATEGORIAREPOSITORY
@Autowired
private CategoryRepository categoryRepository;
	
	@GetMapping("/bookstore")
	
	public String showBooks(Model model) {
		List<Book> booklist; // LUODAAN LISTA MIHIN KIRJAT TALLETETAAN
		model.addAttribute("books", bookRepository.findAll());
		return "bookstore";
	}
	
	// TÄMÄ ON REST ENDPOINT JOSTA NÄKEE JSON MUODOSSA KIRJAT!!!
	// HUOMAA RESPONSEBODY
	@GetMapping("/books")
	public @ResponseBody List<Book> getBooks() {
		return (List<Book>)bookRepository.findAll();
	}
	
	// RAKENNETAAN MYÖS MISSÄ PATHVARIABLE JOTTA VOIDAAN VALITA KIRJA ID:N PERUSTEELLA NÄYTETTÄVÄKSI!
	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> getOneBook(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}
	
	// LUODAAN METODI POST:ILLE! ilman tätä et voi käyttää post ominaisuutta!
	@PostMapping("/books")
	public @ResponseBody Book addNewBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	
	// LUODAAN UUSI MAPPING UUDELLE KIRJALLE
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		// LISÄTÄÄN KATEGORIAREPOSITORY MODELIIN ETTÄ SITÄ VOIDAAN KÄYTTÄÄ!
		model.addAttribute("categories", categoryRepository.findAll());
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
		// SAMOIN LISÄTÄÄN TÄNNE ETTÄ MAPPING VOI KÄYTTÄÄ SITÄ
		model.addAttribute("categories", categoryRepository.findAll());
		return "editform";
	}
}
