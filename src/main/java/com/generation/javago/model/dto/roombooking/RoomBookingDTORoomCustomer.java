package com.generation.javago.model.dto.roombooking;

import com.generation.javago.model.dto.customer.GenericCustomerDTO;
import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.entity.RoomBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBookingDTORoomCustomer extends RoomBookingGenericDTO
{

	GenericCustomerDTO customer;
	GenericRoomDTO room;
	
	public RoomBookingDTORoomCustomer(RoomBooking booking)
	{
		super(booking);
		this.customer = new GenericCustomerDTO(booking.getCustomer());
		this.room = new GenericRoomDTO(booking.getRoom());
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		booking.setCustomer(customer.convertToCustomer());
		booking.setRoom(room.ConvertToRoom());
		
		return booking;
	}
}

