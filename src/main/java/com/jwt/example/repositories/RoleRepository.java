package com.jwt.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.example.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
