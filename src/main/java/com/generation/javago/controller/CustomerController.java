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

import com.generation.javago.auth.model.UserInDb;
import com.generation.javago.auth.service.UserRepository;
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
	CustomerRepository cRepo; 
	@Autowired
	UserRepository uRepo;
	

	@GetMapping("customers")
	public List<GenericCustomerDTO> getAll()
	{
		if(cRepo.findAll().isEmpty())
			throw new NoSuchElementException("Customers not found");
		
		return cRepo.findAll().stream().map(customer -> new GenericCustomerDTO(customer)).toList(); 
	}
	
	
	@GetMapping("customers/{id}")
	public CustomerDTOFULL getById(@PathVariable Integer id) {
		if(cRepo.findById(id).isEmpty())
				throw new NoSuchElementException("Customer not found");
		return new CustomerDTOFULL(cRepo.findById(id).get()); 
	}
	
	@DeleteMapping("customers/{id}")
	public void delete(@PathVariable Integer id)
	{
		if(cRepo.findById(id).isEmpty())
			throw new NoSuchElementException("Customer to delete not found");
		
		Customer customer= cRepo.findById(id).get(); 
		
		uRepo.delete(customer.getUser());
	}
	

	@PutMapping("customers/{id}")
	public CustomerDTOOnlyUser update(@RequestBody CustomerDTOOnlyUser customerDTO, @PathVariable Integer id)
	{
		
		UserInDb currentUser = customerDTO.getUserDTO().convertToUser();
		if	(	!currentUser.isValid() 
					||
//				!currentUser.equals(uRepo.findById(cRepo.findById(id).get().getUser().getId()).get())
				!currentUser.isTheSame(cRepo.findById(id).get().getUser())
			)
			throw new InvalidEntityException("Invalid user data");
		
		if(cRepo.findById(id).isEmpty())
			throw new NoSuchElementException("Customer to update not found");
				
		Customer customer= customerDTO.convertToCustomer(); 
		
		if(!customer.isValid())
			throw new InvalidEntityException("Invalid customer data");
	
		customer.setId(id); 
		
		return new CustomerDTOOnlyUser(cRepo.save(customer)); 	
	}
	
	@PostMapping("customers")
	public CustomerDTOOnlyUser insert(@RequestBody CustomerDTOOnlyUser customerDTO)
	{
		if(!customerDTO.getUserDTO().convertToUser().isValid())
			throw new InvalidEntityException("Invalid user data");
		
		Customer toInsert= customerDTO.convertToCustomer();
		if(!toInsert.isValid())
			throw new InvalidEntityException("Invalid customers data");
		
		
		return new CustomerDTOOnlyUser(cRepo.save(toInsert)); 
	}
}
