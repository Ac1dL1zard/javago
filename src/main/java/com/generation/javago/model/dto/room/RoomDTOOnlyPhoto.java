package com.generation.javago.model.dto.room;

import java.util.HashSet;
import java.util.List;

import com.generation.javago.model.dto.photo.GenericPhotoDTO;
import com.generation.javago.model.entity.Photo;
import com.generation.javago.model.entity.Room;

public class RoomDTOOnlyPhoto extends GenericRoomDTO
{
	
	private List<GenericPhotoDTO> photoDTO; 

	public RoomDTOOnlyPhoto(Room stanza)
	{
		super(stanza);
	
		photoDTO = stanza.getPhotos().stream().map   (
														photo -> 
														new GenericPhotoDTO(photo)
													  ).toList(); 
	}

	@Override
	public Room convertToRoom()
	{
		Room stanza = super.convertToRoom();  
		
		stanza.setPhotos
		
			( new HashSet<>(photoDTO.stream().map 
				(
					phoDTO ->
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
