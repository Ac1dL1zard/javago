package com.generation.javago.model.dto.roombooking;

import java.util.List;

import com.generation.javago.model.dto.season.GenericSeasonDTO;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.entity.Season;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomBookingDTOOnlyList extends RoomBookingGenericDTO
{

	List<GenericSeasonDTO> seasons;
	
	public RoomBookingDTOOnlyList(RoomBooking booking)
	{
		super(booking);
		this.seasons = booking.getSeasons()
				.stream()
				.map(season -> new GenericSeasonDTO(season))
				.toList();
	}
	
	@Override
	public RoomBooking convertToRoomBooking()
	{
		RoomBooking booking = super.convertToRoomBooking();
		
		List<Season> converted = seasons
									.stream()
									.map(seasonDTO -> seasonDTO.convertToSeason())
									.toList();
		
		booking.setBookingSeasons(converted);
		
		return booking;
	}
}
