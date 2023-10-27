package com.generation.javago.model.dto.roombooking;

import java.time.LocalDate;

import com.generation.javago.model.entity.RoomBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingGenericDTO 
{
	protected int id;
	protected LocalDate checkin_date,checkout_date;
	protected Integer n_guest;
	protected boolean save;
	
	public RoomBookingGenericDTO(RoomBooking booking)
	{
		this.id= booking.getId();
		this.checkin_date = booking.getCheckin_date();
		this.checkout_date = booking.getCheckout_date();
		this.n_guest = booking.getN_guest();
		this.save = booking.isSave();
	}
	
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = new RoomBooking();
		
		booking.setId(id);
		booking.setCheckin_date(checkin_date);
		booking.setCheckout_date(checkout_date);
		booking.setN_guest(n_guest);
		booking.setSave(save);
		
		return booking;
	}
}
