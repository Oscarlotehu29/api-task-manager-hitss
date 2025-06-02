package com.hitss.project_task_manager.services;

import java.util.List;
import java.util.Optional;

import com.hitss.project_task_manager.entities.Person;


public interface PersonService {
	List<Person> findAll();
	
	Optional<Person> findById(Long id);
	
	Person save(Person person);
	
	Optional<Person> update(Long id, Person person);
	
	Optional<Person> delete(Long id);
}
