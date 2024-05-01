package com.jwt.kranthi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.kranthi.models.User;

public interface ExportCsvUserDataRepository extends JpaRepository<User, Integer> {
	
}
