package com.jwt.kranthi.bankaccounts;

import java.sql.ResultSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwt.kranthi.models.User;

public interface BankAccountRepository extends JpaRepository<User, String> {

	public User findByEmail(String email);
	
	@Query(value=":query", nativeQuery = true)
	public ResultSet executeQuery(String query);
}