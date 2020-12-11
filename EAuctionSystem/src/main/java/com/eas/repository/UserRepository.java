package com.eas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eas.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	

}


