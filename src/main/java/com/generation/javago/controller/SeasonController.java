package com.generation.javago.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javago.controller.util.InvalidEntityException;
import com.generation.javago.model.dto.season.GenericSeasonDTO;
import com.generation.javago.model.dto.season.SeasonDTOFull;
import com.generation.javago.model.entity.Season;
import com.generation.javago.model.repostiory.SeasonRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class SeasonController
{
	@Autowired
	SeasonRepository repo; 
	
	@GetMapping("seasons")
	public List<GenericSeasonDTO> getAll(){
		return repo.findAll().stream().map(season -> new GenericSeasonDTO(season)).toList(); 
	}
	@GetMapping("seasons/{id}")
	public SeasonDTOFull getById(@PathVariable Integer id) {
		if(repo.findById(id).isEmpty())
				throw new NoSuchElementException("Season not found");
		return new SeasonDTOFull(repo.findById(id).get()); 
	}
	
	@DeleteMapping("seasons/{id}")
	public void delete(@PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Season to delete not found");
		Season season= repo.findById(id).get(); 
		repo.delete(season); 
	}
	
	@PutMapping("seasons/{id}")
	public GenericSeasonDTO update(@RequestBody GenericSeasonDTO seasonDTO, @PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Season to update not found");
		
		Season season= seasonDTO.convertToSeason(); 
		
		if(!season.isValid())
			throw new InvalidEntityException("Updated season data invalid");
		
		season.setId(id); 
		
		return new GenericSeasonDTO(repo.save(season)); 
	}
}
