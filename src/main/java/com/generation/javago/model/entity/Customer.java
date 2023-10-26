package com.generation.javago.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity
{

	private String name; 
	private String surname; 
	private LocalDate dob; 
	

	@ManyToOne(fetch = FetchType.EAGER)   // Collegamento tra gli ordini(Many) e il cliente(One)
	@JoinColumn(name="id_user")         // Collega l'ordine al cliente tramite l'id preso dal db
	User user; 
	
	
	
	@OneToMany(mappedBy="customer",fetch = FetchType.EAGER)
	List<RoomBooking> bookings; 
	
	
	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<String>(); 
		final LocalDate da= LocalDate.parse("1900-01-01"); 
		if (name==null)
			errors.add("Missing or invalid value for field 'name"); 
		if (surname==null)
			errors.add("Missing or invalid value for field 'surname"); 
		if (dob.isBefore(da) || dob.isAfter(LocalDate.now()) )	
			errors.add("invalid value for field 'dob"); 
		return errors;
	}

}
