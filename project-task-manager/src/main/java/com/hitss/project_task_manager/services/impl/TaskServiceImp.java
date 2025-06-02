package com.hitss.project_task_manager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.project_task_manager.entities.Task;
import com.hitss.project_task_manager.repositories.TaskRepository;
import com.hitss.project_task_manager.services.TaskService;

@Service
public class TaskServiceImp implements TaskService{

	
	@Autowired
	private TaskRepository taskRepository;

	@Transactional(readOnly = true)
	@Override
	public Optional<Task> findById(Long id) {
		
		return taskRepository.findById(id);
	}

	@Transactional
	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Transactional
	@Override
	public Optional<Task> update(Long id, Task task) {
		
		Optional<Task> taskOpt = taskRepository.findById(id);
		
		if(taskOpt.isPresent()) {
			Task taskBd = taskOpt.get();
			
			taskBd.setDescription(task.getDescription());
			taskBd.setUser(task.getUser());
			taskBd.setCategory(task.getCategory());
			
			return Optional.of(taskRepository.save(taskBd));
		}
		
		return Optional.empty();
	}

	@Transactional
	@Override
	public Optional<Task> delete(Long id) {
		
		Optional<Task> taskOp = taskRepository.findById(id);
		taskOp.ifPresent(taskRepository::delete);
		return taskOp;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Task> findAll(Long userId) {
		return taskRepository.findByUserId(userId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Task> findByCategoryId(Long category_id) {
		
		return taskRepository.findByCategoryId(category_id);
	}
	
}
