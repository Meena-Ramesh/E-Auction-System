package com.eas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eas.entity.User;
import com.eas.entity.UserLogin;
import com.eas.exception.InvalidInputDataException;
import com.eas.service.UserService;

@CrossOrigin("http://localhost:3000")
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
		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 * MyUserDetails currentUser = (MyUserDetails) auth.getPrincipal();
		 * System.out.println(currentUser.getUsername());
		 */
		User user = userService.findUserById(userId)
				.orElseThrow(() -> new InvalidInputDataException("User doesn't exist with user id " + userId));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> validateUser(@RequestBody UserLogin userLogin) {
		System.out.println("In controller");
		User user = userService.findUserById(userLogin.getUserId())
				.orElseThrow(() -> new InvalidInputDataException("User doesn't exist with user id " + userLogin.getUserId()));
		boolean status = userService.validateUser(userLogin.getUserId(), userLogin.getPassword());
		if (status)
		{
			String actualUserType = user.getUserType().toString();
			if(userLogin.getUserType().equals(actualUserType) || (actualUserType.equals("BOTH") && !userLogin.getUserType().equals("ADMIN")))
				return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
			else throw new InvalidInputDataException("Access Denied");
		}
		else
			throw new InvalidInputDataException("UserId and Password does not match");

	}
	
	
	@GetMapping("")
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}

}
