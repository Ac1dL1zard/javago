package com.generation.javago.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javago.controller.util.InvalidEntityException;
import com.generation.javago.model.dto.customer.CustomerDTOFULL;
import com.generation.javago.model.dto.customer.CustomerDTOOnlyUser;
import com.generation.javago.model.dto.customer.GenericCustomerDTO;
import com.generation.javago.model.entity.Customer;
import com.generation.javago.model.repostiory.CustomerRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CustomerController
{
	@Autowired
	CustomerRepository repo; 
	

	@GetMapping("customers")
	public List<GenericCustomerDTO> getAll(){
		return repo.findAll().stream().map(customer -> new GenericCustomerDTO(customer)).toList(); 
	}
	
	
	@GetMapping("customers/{id}")
	public CustomerDTOFULL getById(@PathVariable Integer id) {
		if(repo.findById(id).isEmpty())
				throw new NoSuchElementException("Customer not found");
		return new CustomerDTOFULL(repo.findById(id).get()); 
	}
	
	@DeleteMapping("customers/{id}")
	public void delete(@PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Customer to delete not found");
		Customer customer= repo.findById(id).get(); 
		repo.delete(customer); 
	}
	

	@PutMapping("customers/{id}")
	public CustomerDTOOnlyUser update(@RequestBody CustomerDTOOnlyUser customerDTO, @PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Customer to update not found");
		
		Customer customer= customerDTO.convertToCustomer(); 
	
		
		customer.setId(id); 
		
		return new CustomerDTOOnlyUser(repo.save(customer)); 	
	}
	
	@PostMapping("customers")
	public CustomerDTOOnlyUser insert(@RequestBody CustomerDTOOnlyUser customerDTO)
	{
		Customer toInsert= customerDTO.convertToCustomer(); 
		if(!toInsert.isValid())
			throw new InvalidEntityException("Invalid customers data");
		
		
		return new CustomerDTOOnlyUser(repo.save(toInsert)); 
	}
}
