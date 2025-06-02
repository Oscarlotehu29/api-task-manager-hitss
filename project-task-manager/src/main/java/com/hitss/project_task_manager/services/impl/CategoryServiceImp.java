package com.hitss.project_task_manager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.project_task_manager.entities.Category;
import com.hitss.project_task_manager.repositories.CategoryRepository;
import com.hitss.project_task_manager.services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Category> findAll(Long userId) {
		return categoryRepository.findByUserId(userId);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Category> findById(Long id) {
		
		return categoryRepository.findById(id);
	}

	@Override
	@Transactional
	public Category save(Category category) {
		
		return categoryRepository.save(category);
	}

	@Transactional
	@Override
	public Optional<Category> update(Long id, Category category) {

		Optional<Category> categoryOp = categoryRepository.findById(id);
		
		if(categoryOp.isPresent()) {
			Category categoryBd = categoryOp.get();
			
			categoryBd.setName(category.getName());
			categoryBd.setUser(category.getUser());
			return Optional.of(categoryRepository.save(categoryBd));
		}
		
		return categoryOp;
	}

	@Transactional
	@Override
	public Optional<Category> delete(Long id) {
		
		Optional<Category> categoryOp = categoryRepository.findById(id);
		categoryOp.ifPresent(categoryRepository::delete);
		
		return categoryOp;
	}
	
}
