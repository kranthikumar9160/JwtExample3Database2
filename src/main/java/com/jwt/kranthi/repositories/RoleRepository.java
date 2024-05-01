package com.jwt.kranthi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.kranthi.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
