package com.generation.javago.model.dto.roombooking;

import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.entity.RoomBooking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomBookingDTOOnlyRoom extends RoomBookingGenericDTO
{

	GenericRoomDTO roomDTO;
	
	public RoomBookingDTOOnlyRoom(RoomBooking booking)
	{
		super(booking);
		this.roomDTO = new GenericRoomDTO(booking.getRoom());
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		booking.setRoom(roomDTO.convertToRoom());
		
		return booking;
	}
}
