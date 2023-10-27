package com.generation.javago.model.dto.room;

import java.util.List;

import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Room;
import com.generation.javago.model.entity.RoomBooking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTOFull extends GenericRoomDTO
{
	private List<RoomBookingGenericDTO> bookings;

	public RoomDTOFull(Room stanza)
	{
		super(stanza);
		bookings = stanza.getBookings().stream().map (
														book -> 
														new RoomBookingGenericDTO(book)
													  ).toList();
	}

	@Override
	public Room ConvertToRoom()
	{
		Room stanza = new Room();
		stanza.setId(id);
		stanza.setName(name);
		stanza.setImgUrl(imgUrl);
		stanza.setCapacity(capacity);
		stanza.setBasePrice(basePrice);
		stanza.setNotes(notes);
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
