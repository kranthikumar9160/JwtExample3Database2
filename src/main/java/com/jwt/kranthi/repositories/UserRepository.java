package com.jwt.kranthi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.kranthi.models.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	public User findByEmail(String email);
}
