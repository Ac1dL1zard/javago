package com.generation.javago.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javago.controller.util.InvalidEntityException;
import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.dto.room.RoomDTOFull;
import com.generation.javago.model.entity.Room;
import com.generation.javago.model.repostiory.RoomRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class RoomController
{
	@Autowired
	RoomRepository repo; 
	
	
	@GetMapping("rooms")
	public List<GenericRoomDTO> getAll(){
		return repo.findAll().stream().map(room -> new GenericRoomDTO (room)).toList(); 
	}
	
	
	@GetMapping("rooms/{id}")
	public RoomDTOFull getById(@PathVariable Integer id) {
		if(repo.findById(id).isEmpty())
				throw new NoSuchElementException("Room not found");
		return new RoomDTOFull(repo.findById(id).get()); 
	}
	
	
	@DeleteMapping("rooms/{id}")
	public void delete(@PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Room to delete not found");
		Room room= repo.findById(id).get(); 
		repo.delete(room); 
	}
	
	
	
	@PutMapping("rooms/{id}")
	public GenericRoomDTO update(@RequestBody GenericRoomDTO roomDTO, @PathVariable Integer id)
	{
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Room to update not found");
		
		Room room= roomDTO.ConvertToRoom(); 
		
		if(!room.isValid())
			throw new InvalidEntityException("Updated room data invalid");
		
		room.setId(id); 
		
		return new GenericRoomDTO(repo.save(room)); 
	}
	
	@PostMapping("rooms")
		public GenericRoomDTO insert(@RequestBody GenericRoomDTO roomDTO)
		{
			Room toInsert= roomDTO.ConvertToRoom(); 
			if(!toInsert.isValid())
				throw new InvalidEntityException("Invalid room data");
			
			return new GenericRoomDTO(repo.save(toInsert)); 
		}
	
		
}
