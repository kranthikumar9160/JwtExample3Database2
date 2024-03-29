package com.jwt.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.example.models.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	public User findByEmail(String email);
}
