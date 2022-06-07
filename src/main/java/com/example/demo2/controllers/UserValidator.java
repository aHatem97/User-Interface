package com.example.demo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo2.entities.User;
import com.example.demo2.services.UserService;

@Component
public class UserValidator implements Validator 
{
	@Autowired
	UserService userService;

    @Override
    public boolean supports(Class<?> aClass) 
    {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) 
    {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) 
        {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
        try {
			if (userService.getUserByUsername(user.getUsername()) != null) 
			{
			    errors.rejectValue("username", "Duplicate.userForm.username");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) 
        {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) 
        {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}