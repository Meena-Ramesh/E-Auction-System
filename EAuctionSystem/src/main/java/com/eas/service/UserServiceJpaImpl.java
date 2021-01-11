package com.eas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eas.entity.User;
import com.eas.exception.InvalidInputDataException;
import com.eas.repository.UserRepository;

@Service
public class UserServiceJpaImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean validateUser(int userId, String password) {
		User user = userRepository.findById(userId).orElseThrow(() -> new InvalidInputDataException("User doesn't exist"));
		if(user.getPassword().equals(password))
			return true;
		return false;
	}

	@Override
	public Optional<User> findUserById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User createAccount(User newUser) {
		return userRepository.save(newUser);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

}
