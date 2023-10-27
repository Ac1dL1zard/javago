package com.generation.javago.model.dto.user;

import com.generation.javago.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericUserDTO {
	
	protected Integer id;

	protected String email; 
	protected String password; 
	protected Integer level; 
	protected String img_url; 
	
	
	public GenericUserDTO() {}
	
	public GenericUserDTO(User u) {

		this.id= u.getId();
		this.email=u.getEmail(); 
		this.password=u.getPassword(); 
		this.level= u.getLevel(); 
		this.img_url= u.getImg_url(); 
	}
	
	public User convertToUser() {
		User res=new User(); 
		res.setId(id);
		res.setEmail(email);
		res.setPassword(password);
		res.setLevel(level);
		res.setImg_url(img_url);
		return res; 
	}
}
