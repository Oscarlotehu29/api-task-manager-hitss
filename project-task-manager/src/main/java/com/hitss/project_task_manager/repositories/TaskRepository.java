package com.hitss.project_task_manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.project_task_manager.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findByUserId(Long userId);
	List<Task> findByCategoryId(Long category_id);
}
