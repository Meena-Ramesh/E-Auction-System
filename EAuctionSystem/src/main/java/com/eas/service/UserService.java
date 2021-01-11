package com.eas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.eas.entity.User;

public interface UserService {

	boolean validateUser(int userId, String password);

	Optional<User> findUserById(int userId);

	User createAccount(User newUser);

	List<User> getAllUser();
	
}
