package com.generation.javago.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

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

import com.generation.javago.auth.service.UserRepository;
import com.generation.javago.controller.util.InvalidEntityException;
import com.generation.javago.model.dto.photo.GenericPhotoDTO;
import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.dto.room.RoomDTOFull;
import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Photo;
import com.generation.javago.model.entity.Room;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.repostiory.PhotoRepository;
import com.generation.javago.model.repostiory.RoomBookingRepository;
import com.generation.javago.model.repostiory.RoomRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class RoomController
{
	@Autowired
	RoomRepository repo; 
	
	@Autowired
	PhotoRepository repoPhoto;
	
	@Autowired
	UserRepository repoUser; 
	
	@Autowired
	RoomBookingRepository repoBookings; 
	
	@PostMapping("rooms/available")
	public List<GenericRoomDTO> getAllBeetweenDate(@RequestBody RoomBookingGenericDTO booking){
		LocalDate checkin_date=booking.getCheckin_date(), checkout_date= booking.getCheckout_date(); 
		List<LocalDate> dateList = new ArrayList<>();
		LocalDate currentDate = checkin_date;

		while (!currentDate.isAfter(checkout_date)) {
		    dateList.add(currentDate);
		    currentDate = currentDate.plusDays(1);
		}   
		List<RoomBooking> allBookings= repoBookings.findAll();
		
		List<Room> allRoom= repo.findAll(); 
		
		for (RoomBooking singleBooking : allBookings) {
			if(singleBooking.isBooked(dateList))
				allRoom.remove(singleBooking.getRoom()); 
		}
		return allRoom.stream().map(room -> new  GenericRoomDTO(room)).toList(); 

	}
	
	
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
	
	
	@DeleteMapping("rooms/{id}/users/{idUser}")
	public void delete(@PathVariable Integer id, @PathVariable Integer idUser, HttpServletResponse response) throws IOException
	{
		if (!repoUser.findById(idUser).get().isEmployed()) {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			 return; 
		}
		
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Room to delete not found");
		Room room= repo.findById(id).get(); 
		repo.delete(room); 
	}
	
	
	
	@PutMapping("rooms/{id}/users/{idUser}")
	public GenericRoomDTO update(@RequestBody GenericRoomDTO roomDTO,
			@PathVariable Integer idUser, @PathVariable Integer id, HttpServletResponse response) throws IOException
	{
		if (!repoUser.findById(idUser).get().isEmployed()) {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			 return null; 
		}
		if(repo.findById(id).isEmpty())
			throw new NoSuchElementException("Room to update not found");
		
		Room room= roomDTO.convertToRoom(); 
		
		if(!room.isValid())
			throw new InvalidEntityException("Updated room data invalid");
		
		room.setId(id); 
		
		return new GenericRoomDTO(repo.save(room)); 
	}
	
	@PostMapping("rooms/users/{idUser}")
		public GenericRoomDTO insert(@RequestBody GenericRoomDTO roomDTO
				, HttpServletResponse response,@PathVariable Integer idUser) throws IOException
		{
			if (!repoUser.findById(idUser).get().isEmployed()) {
				 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				 return null; 
			}
			Room toInsert= roomDTO.convertToRoom(); 
			if(!toInsert.isValid())
				throw new InvalidEntityException("Invalid room data");
			
			return new GenericRoomDTO(repo.save(toInsert)); 
			
		}
	
	
	@PostMapping("rooms/{idRoom}/img/users/{idUser}")
	public GenericPhotoDTO insertPhoto(@RequestBody GenericPhotoDTO photoDTO, @PathVariable Integer idRoom
			, HttpServletResponse response,@PathVariable Integer idUser) throws IOException
	{
		if (!repoUser.findById(idUser).get().isEmployed()) {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			 return null; 
		}
		Room toInsert= repo.findById(idRoom).get(); 
		if(repo.findById(idRoom).isEmpty())
			throw new NoSuchElementException("Room not found");
		Photo photo= photoDTO.convertToPhoto(); 
		if (!photo.isValid())
			throw new InvalidEntityException("Invalid photo data");
		photo.setRoom(toInsert); 
		
		toInsert.getPhotos().add(photo);

		
		return new GenericPhotoDTO(repoPhoto.save(photo)); 
	}
	
	
	
	
	@DeleteMapping("rooms/{idRoom}/img/{idImg}/users/{idUser}")
	public void  deleteImg(@PathVariable Integer idRoom, @PathVariable Integer idImg
			,HttpServletResponse response,@PathVariable Integer idUser) throws IOException
	{
		if (!repoUser.findById(idUser).get().isEmployed()) {
			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			 return; 
		}
		if(repo.findById(idRoom).isEmpty())
			throw new NoSuchElementException("Room to delete photo not found");
		if(repoPhoto.findById(idImg).isEmpty())
			throw new NoSuchElementException("Photo not found");
		
		Photo photo= repoPhoto.findById(idImg).get(); 
		
		repoPhoto.delete(photo); 
	}
	
	@GetMapping("rooms/img")
	public List<GenericPhotoDTO> getAllImg(){
		return repoPhoto.findAll().stream().map(photo -> new GenericPhotoDTO(photo)).toList(); 
	}
	@GetMapping("rooms/{id}/img")
	public Set<GenericPhotoDTO> getAllImgOfaRoom(@PathVariable Integer id){
		
		if (repo.findById(id).isEmpty())
			throw new NoSuchElementException("Room not found");
		Room room= repo.findById(id).get(); 
		
		 return room.getPhotos().stream()
			        .map(photo -> new GenericPhotoDTO(photo))
			        .collect(Collectors.toSet());
		
	}
		
}
