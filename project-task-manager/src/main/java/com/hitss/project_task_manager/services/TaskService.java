package com.hitss.project_task_manager.services;

import java.util.List;
import java.util.Optional;

import com.hitss.project_task_manager.entities.Task;

public interface TaskService {
	List<Task> findAll(Long userId);
	
	List<Task> findByCategoryId(Long category_id);
	
	Optional<Task> findById(Long id);
	
	Task save(Task task);
	
	Optional<Task> update(Long id, Task task);
	
	Optional<Task> delete(Long id);
	
}
