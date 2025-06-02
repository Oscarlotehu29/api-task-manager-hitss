package com.hitss.project_task_manager.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.project_task_manager.entities.User;
import com.hitss.project_task_manager.services.UserService;
import com.hitss.project_task_manager.validation.UserValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidation userValidation;
	
	@GetMapping("/listUsers")
	private List<User> list(){
		
		return userService.findAll();
	}
	
	@GetMapping("/{id}/getUserById")
	private ResponseEntity<?> getUserById(@PathVariable Long id){
		Optional<User> userOpt = userService.findById(id);
		
		if(userOpt.isPresent()) return ResponseEntity.ok(userOpt.orElseThrow());
		
		return ResponseEntity.notFound().build();
		
	}	
	
	@PostMapping("/saveUser")
	private ResponseEntity<?> saveUser(@Valid @RequestBody User user, 
			BindingResult result){
		
		userValidation.validate(user, result);
		if(result.hasFieldErrors())
			return validation(result);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	

	@PutMapping("/{user_id}")
	private ResponseEntity<?> update(@PathVariable Long user_id, @RequestBody User user){
		
		Optional<User> userOpt = userService.findById(user_id);
		
		if(userOpt.isPresent()) return ResponseEntity.ok(userService.update(user_id, user));
		
		return ResponseEntity.notFound().build();
	}
	
	private ResponseEntity<?> validation(BindingResult result){
		Map<String, String> errors = new HashMap<>();
		
		result.getFieldErrors().forEach(
				error -> errors.put(error.getField(), "The field " + error.getField() + " " + error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
	
    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidation);
    }
	
}
