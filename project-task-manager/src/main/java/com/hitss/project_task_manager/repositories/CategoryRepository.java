package com.hitss.project_task_manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.project_task_manager.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByUserId(Long userId);
}
