package com.hh.bookstore.sof23.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hh.bookstore.sof23.domain.Category;
import com.hh.bookstore.sof23.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public String showCategories(Model model) {
		List<Category> categories;
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}

	@GetMapping("/newcategory")
	public String newCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoryform";
	}
	
	@PostMapping("/savecategory")
	public String newCategory(@ModelAttribute Category category, Model model) {
		categoryRepository.save(category);
		return "redirect:/categories";
	}
}
