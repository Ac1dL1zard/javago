package com.generation.javago.auth.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.auth.model.UserInDb;

public interface UserRepository extends JpaRepository<UserInDb, Integer>
{
	Optional<UserInDb> findByUsername(String username);
}
