package com.generation.javago.model.dto.user;

import com.generation.javago.auth.model.UserInDb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericUserDTO {
	
	protected Integer id;

	protected String username; 
	protected String password; 
	protected boolean employed; 
	
	
	public GenericUserDTO() {}
	
	public GenericUserDTO(UserInDb u) {

		this.id= u.getId();
		this.username=u.getUsername(); 
		this.password=u.getPassword(); 
		this.employed= u.isEmployed(); 
	}
	
	public UserInDb convertToUser() {
		UserInDb res=new UserInDb(); 
		res.setId(id);
		res.setUsername(username);
		res.setPassword(password);
		res.setEmployed(employed);
		return res; 
	}
}
