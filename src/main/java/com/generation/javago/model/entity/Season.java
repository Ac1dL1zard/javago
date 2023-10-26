package com.generation.javago.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Season extends BaseEntity
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
//	id int primary key auto_increment,
//    season_name varchar(100),
//    starting_date date,
//    end_date date,
//    price_modifer int
	
	private String seasonName;
	private LocalDate startingDate;
	private LocalDate endDate;
	private Integer priceModifer;
	
	@ManyToMany(mappedBy = "bookingsBySeasons", fetch = FetchType.EAGER)
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
