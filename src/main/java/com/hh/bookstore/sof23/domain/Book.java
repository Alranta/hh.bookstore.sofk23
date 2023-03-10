package com.hh.bookstore.sof23.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// ENTITY TARKOITTAA YHTÄ TABLEA RELAATIOTIETOKANNASSA!
@Entity
public class Book {
	// KIRJAN ATTRIBUUTIR
	// LISÄTÄÄN @Id ANNOTAATIOLLA PÄÄAVAINSARAKE KIRJALLE
	@Id
	// LUODAAN MYÖS AUTOMAATTISESTI GENEROITU ID @GeneratedValue ANNOTAATIOLLA!
	@GeneratedValue(strategy = GenerationType.AUTO) // LUO AUTOMAATTISESTI TIETOKANTAAN ID
	private Long id;
	private String title;
	private String author;
	// VOITAISIIN MYÖS KÄYTTÄÄ @Column ANNOTAATIOTA JOS HALUAISIMME KÄYTTÄÄ year
	// PARAMETRIÄ! SQL ON VARANNUT SANAN YEAR ITSELLEEN!
	private Integer releaseYear;
	private String isbn;
	private double price;
	
	
	// TEHDÄÄN LIITOS TAULUUN CATEGORY
	// TÄSSÄ VOI OLLA MONTA KIRJAA YHDESSÄ CATEGORIASSA!
	@ManyToOne
	//JoinColumn LIITTÄÄ TÄHÄN TAULUUN CATEGORIAN CATEGORYIDLLÄ
	@JsonIgnore
	@JoinColumn(name = "categoryid")
	private Category category;

	// LUODAAN KONSTRUKTORIIN MYÖS CATEGORY CATEGORY MIHIN SE KUULUU!
	public Book(String title, String author, Integer releaseYear, String isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	public Book() {
		super();
	}

	// LUODAAN MYÖS UUSI KONSTRUKTORI MISSÄ ID!

	// LUODAAN MYÖS GETTEREIHIN JA SETTEREIHIN ID-ARVOLLE

	public String getTitle() {
		return title;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// MUISTA MYÖS LUODA UUSI TOSTRING
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", releaseYear=" + releaseYear + ", isbn="
				+ isbn + ", price=" + price + ", category=" + category + "]";
	}

}
