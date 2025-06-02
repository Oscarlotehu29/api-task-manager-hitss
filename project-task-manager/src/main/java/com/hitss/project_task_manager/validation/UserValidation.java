package com.hitss.project_task_manager.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hitss.project_task_manager.entities.User;
import com.hitss.project_task_manager.services.UserService;

@Component
public class UserValidation implements Validator{

	@Autowired
	private UserService userService;
	private final String MESSAGE_FIELD_REQUIRED = " is required.";
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getPerson() == null) errors.rejectValue("person", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getEmail() == null || user.getEmail().isBlank()) errors.rejectValue("email", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getPasswd() == null || user.getPasswd().isBlank()) errors.rejectValue("passwd", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getUserName() == null || user.getUserName().isBlank()) errors.rejectValue(" userName", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getPerson().getName() == null || user.getPerson().getName().isBlank()) errors.rejectValue("person: {\"name\"}", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getPerson().getFather_last_name() == null || user.getPerson().getFather_last_name().isBlank()) errors.rejectValue("person: {\"father_last_name\"}", null, MESSAGE_FIELD_REQUIRED);
		
		if(user.getPasswd().length() < 8) errors.rejectValue("passwd", null, " must have a minimum of 8 characters.");
		
		if(user.getUserName().length() > 15) errors.rejectValue("userName", null, " must have a maximum of 15 characters.");
		
		if(user.getPerson().getName().length() > 25) errors.rejectValue("person: {\"name\"}", null, " must have a maximum of 25 characters");
		
		if(user.getPerson().getFather_last_name().length() > 25) errors.rejectValue("person: {\"father_last_name\"}", null, " must have a maximum of 25 characters");
		
		if(userService.existsByEmail(user.getEmail())) errors.rejectValue("email", null, " is already associated with an existing user.");
		
		if(userService.existsByUserName(user.getUserName())) errors.rejectValue("userName", null, " is already a user with an existing user.");
	}

}
