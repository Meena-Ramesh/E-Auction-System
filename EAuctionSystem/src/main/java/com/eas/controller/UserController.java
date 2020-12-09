package com.eas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eas.entity.User;
import com.eas.exception.InvalidInputDataException;
import com.eas.service.UserService;

@RestController
@RequestMapping("eas/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public ResponseEntity<User> createAccount(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createAccount(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int userId) {
		User user = userService.findUserById(userId).orElseThrow(() -> new InvalidInputDataException("User doesn't exist with user id " + userId));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/{password}")
	public ResponseEntity<String> validateUser(@PathVariable("id") int userId, @PathVariable("password") String password) {
		boolean status = userService.validateUser(userId, password);
		if(status) 
			return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("User Id and password do not match. Log in attempt failed", HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
