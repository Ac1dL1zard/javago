package com.generation.javago.model.repostiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.auth.model.UserInDb;
import com.generation.javago.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	Optional<Customer> findByUser(UserInDb username);
}
