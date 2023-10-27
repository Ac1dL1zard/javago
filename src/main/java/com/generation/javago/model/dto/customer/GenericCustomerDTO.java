package com.generation.javago.model.dto.customer;

import java.time.LocalDate;

import com.generation.javago.model.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericCustomerDTO{
	
	protected Integer id;
	protected String name; 
	protected String surname; 
	protected LocalDate dob; 
	
	public GenericCustomerDTO() {}
	
	public GenericCustomerDTO(Customer c) {
		this.id=c.getId();
		this.name=c.getName(); 
		this.surname=c.getSurname(); 
		this.dob=c.getDob(); 
	}
	
	public Customer convertToCustomer() {
		Customer res=new Customer(); 
		res.setId(id);
		res.setName(name);
		res.setSurname(surname);
		res.setDob(dob);
		return res; 
	}
}
