package com.generation.javago.model.dto.roombooking;

import com.generation.javago.model.dto.customer.GenericCustomerDTO;
import com.generation.javago.model.entity.RoomBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBookingDTOOnlyCustomer extends RoomBookingGenericDTO
{

	GenericCustomerDTO customer;
	
	public RoomBookingDTOOnlyCustomer(RoomBooking booking)
	{
		super(booking);
		this.customer = new GenericCustomerDTO(booking.getCustomer());
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		booking.setCustomer(customer.convertToCustomer());
		
		return booking;
	}
}

