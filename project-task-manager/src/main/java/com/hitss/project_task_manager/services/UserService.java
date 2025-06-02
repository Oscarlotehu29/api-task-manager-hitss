package com.hitss.project_task_manager.services;

import java.util.List;
import java.util.Optional;

import com.hitss.project_task_manager.entities.User;

public interface UserService {
	List<User> findAll();
	
	Optional<User> findById(Long id);
	
	User save(User user);
	
	Optional<User> update(Long id, User user);
	
	Optional<User> delete(Long id);
	
	boolean existsByEmail(String email);
	
	boolean existsByUserName(String userName);
}
