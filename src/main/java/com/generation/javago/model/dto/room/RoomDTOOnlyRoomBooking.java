package com.generation.javago.model.dto.room;

import java.util.List;

import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Room;
import com.generation.javago.model.entity.RoomBooking;

public class RoomDTOOnlyRoomBooking extends GenericRoomDTO
{
	private List<RoomBookingGenericDTO> bookings;

	public RoomDTOOnlyRoomBooking(Room stanza)
	{
		super(stanza);
		bookings = stanza.getBookings().stream().map (
														book -> 
														new RoomBookingGenericDTO(book)
													  ).toList();
		
	}

	@Override
	public Room convertToRoom()
	{
		Room stanza = super.convertToRoom(); 
		stanza.setBookings
			(
				bookings.stream().map	(bookDTO -> 
															{
																RoomBooking ordine = bookDTO.convertToRoomBooking();
																ordine.setRoom(stanza);
																return ordine;
															}
										).toList()
			);
		return stanza;
	}
}
