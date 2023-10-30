package com.generation.javago.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Season extends BaseEntity
{
	
	
	private String seasonName;
	private LocalDate startingDate;
	private LocalDate endDate;
	private Integer priceModifer;
	
	@ManyToMany(mappedBy = "seasons", fetch = FetchType.EAGER)
	private List<RoomBooking> bookingsBySeasons;
	
	
	
	
	
	
	@Override
	public List<String> getErrors() 
	{
		List<String> errors = new ArrayList<>();
	    
		if (seasonName==null)
			errors.add("Missing or invalid value for field 'seasonName");
		if (startingDate == null) 
	        errors.add("Start date cannot be null.");
 
	    if (endDate == null) 
	        errors.add("End date cannot be null.");
	    if (startingDate != null && endDate != null) 
	    {
	        if (startingDate.isAfter(endDate)) 
	            errors.add("Start date cannot be after the end date.");
	    }
	    
	    if (priceModifer<0)
	    	errors.add("Missing or invalid value for field priceModifer");
		        
		return errors;
	}
	
	
	
	

}
