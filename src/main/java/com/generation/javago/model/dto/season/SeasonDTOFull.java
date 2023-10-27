package com.generation.javago.model.dto.season;

import java.util.List;

import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.entity.Season;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeasonDTOFull extends GenericSeasonDTO
{
	private List<RoomBookingGenericDTO> bookingsBySeasonsDTO;
	

	public SeasonDTOFull() {}
	
    public SeasonDTOFull(Season season) 
    {
        super(season);

        
        bookingsBySeasonsDTO = season.getBookingsBySeasons().stream().map(bookingBySeason -> new RoomBookingGenericDTO(bookingBySeason)).toList();
    }

    @Override
    public Season convertToSeason()
    {
    	Season season = super.convertToSeason();
    	season.setBookingsBySeasons(bookingsBySeasonsDTO.stream().map(RoomBookingDTO -> 
    						
    						{
    							RoomBooking roomBooking = RoomBookingDTO.convertToRoomBooking();
    							
    							return roomBooking;
    						}
    							).toList());
    			
        return season;
    }
}


