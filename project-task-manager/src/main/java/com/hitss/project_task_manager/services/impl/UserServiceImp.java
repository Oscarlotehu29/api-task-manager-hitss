package com.hitss.project_task_manager.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.project_task_manager.entities.Person;
import com.hitss.project_task_manager.entities.User;
import com.hitss.project_task_manager.repositories.PersonRepository;
import com.hitss.project_task_manager.repositories.UserRepository;
import com.hitss.project_task_manager.services.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<User> findById(Long id) {

		return userRepository.findById(id);
	}

	@Transactional
	@Override
	public User save(User user) {
		
			Person person = user.getPerson();
			if(person.getId() == null || person.getId() <= 0) {
				person = personRepository.save(person);
				user.setPerson(person);
			}
		
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public Optional<User> update(Long id, User user) {
		
		Optional<User> userOpt = userRepository.findById(id);
		
		if(userOpt.isPresent()) {
			User userBd = userOpt.get();
			Person person = user.getPerson();
			if(person.getId() <= 0) {
				person = personRepository.save(person);
				userBd.setPerson(person);
			}
			userBd.setEmail(user.getEmail());
			userBd.setUserName(user.getUserName());
			userBd.setPasswd(user.getPasswd());
			userBd.setIdUserCreated(user.getIdUserCreated());
			return Optional.of(userRepository.save(userBd));
		}
		return userOpt;
	}

	@Transactional
	@Override
	public Optional<User> delete(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		userOpt.ifPresent(userRepository::delete);
		return userOpt;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

}
