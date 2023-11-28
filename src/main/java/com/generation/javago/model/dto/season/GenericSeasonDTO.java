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
	
	
	protected int id;
	protected String season_name;
	protected LocalDate starting_date;
	protected LocalDate end_date;
	protected Integer price_modifer;
	
	public GenericSeasonDTO() {}
	
	public GenericSeasonDTO(Season season)
	{
		id = season.getId();
		season_name = season.getSeason_name();
		starting_date = season.getStarting_date();
		end_date = season.getEnd_date();
		price_modifer = season.getPrice_modifer();
		
	}
	
	public Season convertToSeason()
	{
		Season season = new Season();
		season.setId(id);
		season.setSeason_name(season_name);
		season.setStarting_date(starting_date);
		season.setEnd_date(end_date);
		season.setPrice_modifer(price_modifer);
		
		return season;
	}

}
