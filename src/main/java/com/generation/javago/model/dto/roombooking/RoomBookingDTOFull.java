package com.generation.javago.model.dto.roombooking;

import java.util.List;

import com.generation.javago.model.dto.customer.GenericCustomerDTO;
import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.dto.season.GenericSeasonDTO;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.entity.Season;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RoomBookingDTOFull extends RoomBookingGenericDTO
{
	GenericCustomerDTO customerDTO;
	
	GenericRoomDTO roomDTO;
	
	List<GenericSeasonDTO> seasonsDTO;
	

	
	public RoomBookingDTOFull(RoomBooking booking)
	{
		super(booking);
		this.customerDTO = new GenericCustomerDTO(booking.getCustomer());
		this.seasonsDTO = booking.getSeasons()
				.stream()
				.map(season -> new GenericSeasonDTO(season))
				.toList();
		this.roomDTO = new GenericRoomDTO(booking.getRoom());
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		booking.setCustomer(customerDTO.convertToCustomer());
		booking.setRoom(roomDTO.convertToRoom());
		List<Season> converted = seasonsDTO
				.stream()
				.map(seasonDTO -> seasonDTO.convertToSeason())
				.toList();

		booking.setBookingSeasons(converted);
		
		return booking;
	}
}
