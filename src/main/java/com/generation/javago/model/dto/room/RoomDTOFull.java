package com.generation.javago.model.dto.room;

import java.util.HashSet;
import java.util.List;

import com.generation.javago.model.dto.photo.GenericPhotoDTO;
import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Photo;
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
	private List<GenericPhotoDTO> photoDTO; 

	public RoomDTOFull(Room stanza)
	{
		super(stanza);
		bookings = stanza.getBookings().stream().map (
														book -> 
														new RoomBookingGenericDTO(book)
													  ).toList();
		
		photoDTO = stanza.getPhotos().stream().map   (
														photo -> 
														new GenericPhotoDTO(photo)
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
		
		stanza.setPhotos
			(
				new HashSet<>(
								photoDTO.stream().map (phoDTO ->
									{
										Photo photo = phoDTO.convertToPhoto(); 
										photo.setRoom(stanza); 
										return photo; 
									}
							).toList())			
			);
		return stanza;
	}
}
