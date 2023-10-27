package com.generation.javago.model.dto.room;

import com.generation.javago.model.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericRoomDTO
{
	protected int id;
	protected String name;
	protected String imgUrl;
	protected Integer capacity;
	protected Double basePrice;
	protected String notes;

	public GenericRoomDTO(Room stanza)
	{
		id = stanza.getId();
		name = stanza.getName();
		imgUrl = stanza.getImgUrl();
		capacity = stanza.getCapacity();
		basePrice = stanza.getBasePrice();
		notes = stanza.getNotes();

	}

	public Room ConvertToRoom()
	{
		Room stanza = new Room();
		stanza.setId(id);
		stanza.setName(name);
		stanza.setImgUrl(imgUrl);
		stanza.setCapacity(capacity);
		stanza.setBasePrice(basePrice);
		stanza.setNotes(notes);
		return stanza;
	}
}
