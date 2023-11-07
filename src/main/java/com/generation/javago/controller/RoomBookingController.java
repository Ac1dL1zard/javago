package com.generation.javago.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import com.generation.javago.auth.model.UserInDb;
import com.generation.javago.auth.service.UserRepository;
import com.generation.javago.controller.util.InvalidEntityException;
import com.generation.javago.model.dto.room.GenericRoomDTO;
import com.generation.javago.model.dto.roombooking.RoomBookingDTOFull;
import com.generation.javago.model.dto.roombooking.RoomBookingDTOOnlyRoom;
import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Customer;
import com.generation.javago.model.entity.Room;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.repostiory.CustomerRepository;
import com.generation.javago.model.repostiory.RoomBookingRepository;
import com.generation.javago.model.repostiory.RoomRepository;
import com.generation.javago.model.repostiory.SeasonRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class RoomBookingController
{
	@Autowired
	RoomRepository roRepo; 
	@Autowired
	RoomBookingRepository rbRepo;
	@Autowired
	CustomerRepository cuRepo;
	@Autowired
	SeasonRepository seRepo;
	@Autowired
	UserRepository usRepo; 

	
	
	
	@GetMapping("bookings/user/{idUser}")
	public List<?> getAll(@PathVariable Integer idUser)
	{
		UserInDb current = usRepo.findById(idUser).get();
		
		
		
		if(!current.isEmployed())
		{
			
			List<RoomBookingGenericDTO> bookingsDTO = rbRepo.findByCustomer(cuRepo.findByUser(current).get()) 
					.stream()
					.map(booking -> new RoomBookingGenericDTO(booking))
					.toList();
			
				return bookingsDTO ;
		}
		
		List<RoomBookingDTOFull> bookingsDTO = rbRepo.findAll()
				.stream()
				.map(booking -> new RoomBookingDTOFull(booking))
				.toList();

		return bookingsDTO;
	}
	
	@GetMapping("bookings/{idBooking}/customers/{idCustomer}")
	public RoomBookingGenericDTO getOneBooking(@PathVariable Integer idBooking,@PathVariable Integer idCustomer) 
	{
		Customer current = cuRepo.findById(idCustomer).get();
		
		Optional<RoomBooking> bookingBox = rbRepo.findById(idBooking);
		
		RoomBooking booking = bookingBox.get();
		
		if(bookingBox.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		return new RoomBookingDTOFull(booking);
	}
	
	@PostMapping("bookings/customers/{idCus}/rooms/{idRoom}")
	public RoomBookingDTOFull insert(@RequestBody RoomBookingGenericDTO dto,@PathVariable Integer idCus, @PathVariable Integer idRoom)
	{
		Optional<Customer> c = cuRepo.findById(idCus);
		if(c.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		Customer customer = c.get();
		
		if(roRepo.findById(idRoom).isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		Room room= roRepo.findById(idRoom).get(); 
		
	
		RoomBooking booking = dto.convertToRoomBooking();	
		if(!booking.isValid())
			throw new InvalidEntityException("Invalid Booking data");
		
		booking.setRoom(room); 
		
		booking.setCustomer(customer);
		
		booking.setBookingSeasons(seRepo.findAll());
		
		booking.setPrice(); 
		
		List<RoomBooking> allBookings= rbRepo.findByRoom(room); 
		
		
		for(RoomBooking bookingDB : allBookings) {
			if(booking.isBooked(bookingDB.getDaysOfBookings()))
				throw new InvalidEntityException("Date NON valide, questa stanza e già prenotata nei giorni inseriti");
		}
		
		RoomBookingDTOFull res= new RoomBookingDTOFull(rbRepo.save(booking)); 
				
		
		
		return res;
		
	}
	
	@PutMapping("bookings/{id}/confirm")
	public RoomBookingDTOOnlyRoom confirmBooking(@PathVariable Integer id)
	{
		Optional<RoomBooking> bo = rbRepo.findById(id);
		if(bo.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		RoomBooking booking = bo.get();
		
		booking.setSaved(true);
		
		return new RoomBookingDTOOnlyRoom(rbRepo.save(booking));
	}
	
	@PutMapping("bookings/{id}")
	public RoomBookingGenericDTO modifyBooking(@PathVariable Integer id, @RequestBody RoomBookingGenericDTO dto)
	{
		Optional<RoomBooking> bo = rbRepo.findById(id);
		if(bo.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
	
		int idRoom= bo.get().getRoom().getId(); 
		
		Room room= roRepo.findById(idRoom).get(); 
		
		
		RoomBooking modified = dto.convertToRoomBooking();
		modified.setId(id); 
		
	
		if (!modified.isValid())
			throw new InvalidEntityException("Invalid booking data");
		modified.setSeasons(seRepo.findAll()); 
		modified.setRoom(room); 
		modified.setPrice(); 
		List<RoomBooking> allBookings= rbRepo.findByRoom(room); 
		allBookings.remove(bo.get()); 
		
		for(RoomBooking bookingDB : allBookings) {
			if(modified.isBooked(bookingDB.getDaysOfBookings()))
				throw new InvalidEntityException("Date NON valide, questa stanza e già prenotata nei giorni inseriti");
		}
		
		
		
		return new RoomBookingGenericDTO(rbRepo.save(modified));
	}
	
	@DeleteMapping("bookings/{id}")
	public void delete (@PathVariable Integer id)
	{
		Optional<RoomBooking> bo = rbRepo.findById(id);
		if(bo.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		rbRepo.deleteById(id);
		
		return;
	}
}
