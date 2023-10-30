package com.generation.javago.model.dto.photo;

import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.entity.Photo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PhotoDTOFull extends GenericPhotoDTO {
	
	GenericRoomDTO roomDTO; 
	
	public PhotoDTOFull(Photo p) {
		super(p); 
		roomDTO= new GenericRoomDTO(p.getRoom()); 
	}
	
	public Photo convertToPhoto() {
		Photo res= super.convertToPhoto(); 
		res.setRoom(roomDTO.convertToRoom());
		return res; 
	}
	
}
 