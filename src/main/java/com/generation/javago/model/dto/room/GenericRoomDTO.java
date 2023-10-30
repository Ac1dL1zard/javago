package com.generation.javago.model.dto.room;

import com.generation.javago.model.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericRoomDTO
{
	protected int id;
	protected String img_url; 
	protected String name;
	protected Integer capacity;
	protected Double basePrice;
	protected String notes;

	public GenericRoomDTO(Room stanza)
	{
		id = stanza.getId();
		name = stanza.getName();
		capacity = stanza.getCapacity();
		basePrice = stanza.getBasePrice();
		notes = stanza.getNotes();
		img_url= stanza.getImg_url(); 

	}

	public Room convertToRoom()
	{
		Room stanza = new Room();
		stanza.setId(id);
		stanza.setName(name);
		stanza.setCapacity(capacity);
		stanza.setBasePrice(basePrice);
		stanza.setNotes(notes);
		stanza.setImg_url(img_url);
		return stanza;
	}
}
