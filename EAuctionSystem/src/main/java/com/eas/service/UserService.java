package com.eas.service;

import java.util.Optional;

import com.eas.entity.User;

public interface UserService {

	boolean validateUser(int userId, String password);

	Optional<User> findUserById(int userId);

	User createAccount(User newUser);
	
}
