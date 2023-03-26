package com.hh.bookstore.sof23.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// REPOSITORYT OVAT AINA INTERFACEJA!!!!!!! MUISTA
public interface BookRepository extends CrudRepository <Book, Long>{

	List<Book> findByTitle(String title);
	// TÄMÄ PERII findAll(); findById(); save(); deleteById();!
	
	// VERTAA JDBC JA JDBCDAO! tämä on sitä varten!
}
