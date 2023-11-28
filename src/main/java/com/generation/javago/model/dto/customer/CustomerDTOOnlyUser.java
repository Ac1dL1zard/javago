package com.generation.javago.model.dto.customer;


import com.generation.javago.model.dto.user.GenericUserDTO;
import com.generation.javago.model.entity.Customer;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CustomerDTOOnlyUser extends GenericCustomerDTO{
	
	private GenericUserDTO userDTO; 
	
	public CustomerDTOOnlyUser() {}
	
	public CustomerDTOOnlyUser(Customer c) {
		super(c); 
		userDTO= new GenericUserDTO(c.getUser()); 
	}
	
	@Override
	public Customer convertToCustomer() {
		Customer res= super.convertToCustomer(); 
		res.setUser(userDTO.convertToUser());
		
		return res; 
	}
}
