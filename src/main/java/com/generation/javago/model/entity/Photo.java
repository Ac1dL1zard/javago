package com.generation.javago.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.generation.javago.model.library.BaseEntity;

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
