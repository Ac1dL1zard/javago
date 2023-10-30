package com.generation.javago.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room extends BaseEntity
{
	private String img_url; 
	private String name;
	private Integer capacity;
	private Double basePrice;
	private String notes;
	
	
	@OneToMany(mappedBy="room", fetch = FetchType.EAGER)
	private List<Photo> photos; 
	
	@OneToMany(mappedBy="room", fetch = FetchType.EAGER)
	private List<RoomBooking> bookings;
	
	@Override
	public List<String> getErrors() 
	{
		List<String> errors = new ArrayList<>();
	    
		if (name==null)
		{
			errors.add("Missing or invalid value for field 'name");
		}
		if (capacity<=0)
			errors.add("Missing or invalid value for field 'capacity");
		if (basePrice<=0)
			errors.add("Missing or invalid value for field 'basePrice");
	    
	    return errors;
	}
	
	
}
