package com.hitss.project_task_manager.services;

import java.util.List;
import java.util.Optional;

import com.hitss.project_task_manager.entities.Category;


public interface CategoryService {
	List<Category> findAll(Long userId);
	
	Optional<Category> findById(Long id);
	
	Category save(Category category);
	
	Optional<Category> update(Long id, Category category);
	
	Optional<Category> delete(Long id);
	
}
