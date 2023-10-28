package com.generation.javago.model.dto.roombooking;

import java.util.List;

import com.generation.javago.model.dto.customer.GenericCustomerDTO;
import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.dto.season.GenericSeasonDTO;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.entity.Season;

public class RoomBookingDTOFull extends RoomBookingGenericDTO
{
	GenericCustomerDTO customer;
	
	GenericRoomDTO room;
	
	List<GenericSeasonDTO> seasons;
	

	
	public RoomBookingDTOFull(RoomBooking booking)
	{
		super(booking);
		this.customer = new GenericCustomerDTO(booking.getCustomer());
		this.seasons = booking.getSeasons()
				.stream()
				.map(season -> new GenericSeasonDTO(season))
				.toList();
		this.room = new GenericRoomDTO(booking.getRoom());
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		booking.setCustomer(customer.convertToCustomer());
		booking.setRoom(room.ConvertToRoom());
		List<Season> converted = seasons
				.stream()
				.map(seasonDTO -> seasonDTO.convertToSeason())
				.toList();

		booking.setBookingSeasons(converted);
		
		return booking;
	}
}
