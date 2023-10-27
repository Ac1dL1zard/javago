package com.generation.javago.model.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	
}
