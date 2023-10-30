package com.generation.javago.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Photo extends BaseEntity {


	String img_url; 
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "room_id")
	Room room; 
	
	@Override
	public List<String> getErrors() {
		
		List<String> errors = new ArrayList<>();
		
		if (img_url==null)
		{
			errors.add("Missing or invalid value for field 'name");
		}
		return errors;
	}
	
}
