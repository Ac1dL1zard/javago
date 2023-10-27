package com.generation.javago.model.dto.season;

import java.time.LocalDate;

import com.generation.javago.model.entity.Season;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericSeasonDTO 
{
	
	
	protected Integer id;
	protected String seasonName;
	protected LocalDate startingDate;
	protected LocalDate endDate;
	protected Integer priceModifer;
	
	public GenericSeasonDTO() {}
	
	public GenericSeasonDTO(Season season)
	{
		id = season.getId();
		seasonName = season.getSeasonName();
		startingDate = season.getStartingDate();
		endDate = season.getEndDate();
		priceModifer = season.getPriceModifer();
		
	}
	
	public Season convertToSeason()
	{
		Season season = new Season();
		season.setId(id);
		season.setSeasonName(seasonName);
		season.setStartingDate(startingDate);
		season.setEndDate(endDate);
		season.setPriceModifer(priceModifer);
		
		return season;
	}

}
