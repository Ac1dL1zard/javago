package com.generation.javago.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.generation.javago.model.library.BaseEntity;

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
	
	
	private String season_name;
	private LocalDate starting_date;
	private LocalDate end_date;
	private Integer price_modifer;
	
	@ManyToMany(mappedBy = "seasons", fetch = FetchType.EAGER)
	private List<RoomBooking> bookingsBySeasons;
	
	
	
	
	
	
	@Override
	public List<String> getErrors() 
	{
		List<String> errors = new ArrayList<>();
	    
		if (season_name==null)
			errors.add("Missing or invalid value for field 'seasonName");
		if (starting_date == null) 
	        errors.add("Start date cannot be null.");
 
	    if (end_date == null) 
	        errors.add("End date cannot be null.");
	    if (starting_date != null && end_date != null) 
	    {
	        if (starting_date.isAfter(end_date)) 
	            errors.add("Start date cannot be after the end date.");
	    }
	    
	    if (price_modifer<0)
	    	errors.add("Missing or invalid value for field priceModifer");
		        
		return errors;
	}
	
	
	
	

}
