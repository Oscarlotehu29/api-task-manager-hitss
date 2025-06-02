package com.hitss.project_task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.project_task_manager.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
