package com.eas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eas.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT CASE WHEN (user.userId = :Id AND user.password = :pwd) THEN true ELSE false END FROM User user")
	boolean validateUser(@Param("Id") int userId,@Param("pwd") String password);

}


