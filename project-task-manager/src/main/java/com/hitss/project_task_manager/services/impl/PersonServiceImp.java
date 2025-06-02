package com.hitss.project_task_manager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.project_task_manager.entities.Person;
import com.hitss.project_task_manager.repositories.PersonRepository;
import com.hitss.project_task_manager.services.PersonService;

@Service
public class PersonServiceImp implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Person> findAll() {

		return personRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}

	@Transactional
	@Override
	public Person save(Person person) {

		return personRepository.save(person);
	}

	@Transactional
	@Override
	public Optional<Person> update(Long id, Person person) {

		Optional<Person> personOp = personRepository.findById(id);
		
		if(personOp.isPresent()) {
			Person personBd = personOp.get();
			
			personBd.setName(person.getName());
			personBd.setFather_last_name(person.getFather_last_name());
			personBd.setMother_last_name(person.getMother_last_name());
			personBd.setBirthDate(person.getBirthDate());
			return Optional.of(personRepository.save(personBd));
		}
		
		return personOp;
	}

	@Transactional
	@Override
	public Optional<Person> delete(Long id) {


		Optional<Person> personOp = personRepository.findById(id);
		
		personOp.ifPresent(personRepository::delete);
		
		return personOp;
	}
	
}
