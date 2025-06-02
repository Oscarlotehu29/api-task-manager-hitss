package com.hitss.project_task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.project_task_manager.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	boolean existsByEmail(String email);
	
	boolean existsByUserName(String userName);
}
