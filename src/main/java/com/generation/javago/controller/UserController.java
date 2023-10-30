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
import com.generation.javago.model.dto.user.GenericUserDTO;
import com.generation.javago.model.entity.User;
import com.generation.javago.model.repostiory.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class UserController
{
	@Autowired
	UserRepository repo; 
	
	
	@GetMapping("users")
	public List<GenericUserDTO> getAll(){
		return repo.findAll().stream().map(user -> new GenericUserDTO(user)).toList(); 
	}
	
	@GetMapping("users/{id}")
	public GenericUserDTO getById(@PathVariable Integer id) {
		if(repo.findById(id).isEmpty())
				throw new NoSuchElementException("User not found");
		return new GenericUserDTO(repo.findById(id).get()); 
	}
	
	@DeleteMapping("users/{id}")
	public void delete(@PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("User to delete not found");
		User user= repo.findById(id).get(); 
		repo.delete(user); 
	}
	
	@PutMapping("users/{id}")
	public GenericUserDTO update(@RequestBody GenericUserDTO userDTO, @PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("User to update not found");
		
		User user= userDTO.convertToUser(); 
		
		
		
		user.setId(id); 
		
		return new GenericUserDTO(repo.save(user)); 
	}
	
	@PostMapping("users")
	public GenericUserDTO insert(@RequestBody GenericUserDTO userDTO)
	{
		User toInsert= userDTO.convertToUser(); 
		if(!toInsert.isValid())
			throw new InvalidEntityException("Invalid user data");
		
		return new GenericUserDTO(repo.save(toInsert)); 
	}

}
