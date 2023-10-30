package com.generation.javago.model.dto.roombooking;

import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.entity.RoomBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBookingDTOOnlyRoom extends RoomBookingGenericDTO
{

	GenericRoomDTO room;
	
	public RoomBookingDTOOnlyRoom(RoomBooking booking)
	{
		super(booking);
		this.room = new GenericRoomDTO(booking.getRoom());
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		booking.setRoom(room.convertToRoom());
		
		return booking;
	}
}
