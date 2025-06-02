package com.hitss.project_task_manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.project_task_manager.entities.Category;
import com.hitss.project_task_manager.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{user_id}/getCategories")
	public List<Category> getCategories(@PathVariable Long user_id){
		return categoryService.findAll(user_id);
	}
	
	@PostMapping("/saveCategory")
	public ResponseEntity<?> saveCategory(@RequestBody Category category){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
	}
	
}
