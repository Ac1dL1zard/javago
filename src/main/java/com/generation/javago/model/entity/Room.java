package com.generation.javago.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room extends BaseEntity
{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private String name;
	private String imgUrl;
	private Integer capacity;
	private Double basePrice;
	private String notes;
	
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
