package com.jwt.example.bankaccounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwt.example.models.User;

public interface BankAccountRepository extends JpaRepository<User, String> {

	public User findByEmail(String email);
	
	@Query(value=":query", nativeQuery = true)
	public void executeQuery(String query);
}