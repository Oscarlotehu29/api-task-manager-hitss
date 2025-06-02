package com.hitss.project_task_manager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.project_task_manager.entities.Task;
import com.hitss.project_task_manager.services.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/{user_id}/getTasks")
	private List<Task> getTasks(@PathVariable Long user_id){
		return taskService.findAll(user_id);
	}
	
	@GetMapping("/{category_id}/getTasksByCategory")
	private List<Task> getTasksByCategory(@PathVariable Long category_id){
		return taskService.findByCategoryId(category_id);
	}
	
	@PostMapping("/saveTask")
	private ResponseEntity<?> saveTask(@RequestBody Task task){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
	}
	
	@GetMapping("/{task_id}/viewTask")
	private ResponseEntity<?> viewTask(@PathVariable Long task_id){
		
		Optional<Task> taskOpt = taskService.findById(task_id);
		
		if(taskOpt.isPresent()) return ResponseEntity.ok(taskOpt.orElseThrow());
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{task_id}")
	private ResponseEntity<?> update(@PathVariable Long task_id, @RequestBody Task task){
		
		Optional<Task> taskOpt = taskService.findById(task_id);
		if(taskOpt.isPresent()) return ResponseEntity.ok(taskService.update(task_id, task).orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{task_id}")
	private ResponseEntity<?> delete(@PathVariable Long task_id){
		
		Optional<Task> taskOpt = taskService.findById(task_id);
		if(taskOpt.isPresent()) return ResponseEntity.ok(taskService.delete(task_id).orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
}
