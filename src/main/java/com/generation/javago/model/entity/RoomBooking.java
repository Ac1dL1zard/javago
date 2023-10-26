package com.generation.javago.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomBooking extends BaseEntity
{
	private LocalDate checkin_date,checkout_date;
	private Double price;
	private Integer n_guest;
	private boolean save;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	Customer customer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "room_id")
	Room room;
	
	@ManyToMany()
	@JoinTable
	(
		name = "roombooking_to_season", 
		joinColumns = @JoinColumn(name = "id_roombooking"), 
		inverseJoinColumns = @JoinColumn(name = "id_season")
	)
	private List<Season> seasons;

	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(checkin_date==null || checkin_date.isAfter(checkout_date))
			errors.add("Missing or invalid value for field 'checkin_date'");
		if(checkout_date==null || checkout_date.isBefore(checkin_date))
			errors.add("Missing or invalid value for field 'checkout_date'");
		if(price==null || price<0)
			errors.add("Missing or invalid value for field 'price'");
		if(n_guest==null || n_guest<=0)
			errors.add("Missing or invalid value for field 'indirizzo'");
		
		return errors;
	}
	
	
}
