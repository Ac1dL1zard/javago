package com.generation.javago.controller;

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

import com.generation.javago.model.dto.roombooking.RoomBookingDTOFull;
import com.generation.javago.model.dto.roombooking.RoomBookingDTOOnlyRoom;
import com.generation.javago.model.dto.roombooking.RoomBookingDTORoomCustomer;
import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Customer;
import com.generation.javago.model.entity.RoomBooking;
import com.generation.javago.model.repostiory.CustomerRepository;
import com.generation.javago.model.repostiory.RoomBookingRepository;
import com.generation.javago.model.repostiory.SeasonRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class RoomBookingController
{
	
	@Autowired
	RoomBookingRepository rbRepo;
	@Autowired
	CustomerRepository cuRepo;
	@Autowired
	SeasonRepository seRepo;
	
	@GetMapping("bookings")
	public List<RoomBookingGenericDTO> getAll()
	{
		List<RoomBookingGenericDTO> bookingsDTO = rbRepo.findAll()
			.stream()
			.map(booking -> new RoomBookingGenericDTO(booking))
			.toList();
		
		return bookingsDTO;
	}
	
	@GetMapping("bookings/admin")
	public List<RoomBookingDTORoomCustomer> getAllwCustomerAndRoom()
	{
		List<RoomBookingDTORoomCustomer> bookingsDTO = rbRepo.findAll()
			.stream()
			.map(booking -> new RoomBookingDTORoomCustomer(booking))
			.toList();
		
		return bookingsDTO;
	}
	
	@GetMapping("bookings/{id}")
	public RoomBookingDTOFull getOneBooking(@PathVariable Integer id) 
	{
		Optional<RoomBooking> booking = rbRepo.findById(id);
		if(booking.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		return new RoomBookingDTOFull(booking.get());	
	}
	
	@PostMapping("bookings/{id}")
	public RoomBookingDTOFull insert(@RequestBody RoomBookingDTOOnlyRoom dto,@PathVariable Integer idCus)
	{
		Optional<Customer> c = cuRepo.findById(idCus);
		if(c.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		Customer customer = c.get();
		
		RoomBooking booking = dto.convertToRoomBooking();		
		
		booking.setCustomer(customer);
		
		booking.setBookingSeasons(seRepo.findAll());
		
		return new RoomBookingDTOFull(rbRepo.save(booking));
		
	}
	
	@PutMapping("bookings/{id}/confirm")
	public RoomBookingDTOOnlyRoom confirmBooking(@PathVariable Integer id)
	{
		Optional<RoomBooking> bo = rbRepo.findById(id);
		if(bo.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		RoomBooking booking = bo.get();
		
		booking.setSave(true);
		
		return new RoomBookingDTOOnlyRoom(rbRepo.save(booking));
	}
	
	@PutMapping("bookings/{id}")
	public RoomBookingDTOOnlyRoom modifyBooking(@PathVariable Integer id, @RequestBody RoomBookingDTOOnlyRoom dto)
	{
		Optional<RoomBooking> bo = rbRepo.findById(id);
		if(bo.isEmpty())
			throw new NoSuchElementException("Non ho trovato nessun elemento con quell'id");
		
		RoomBooking bookingToModify = bo.get();
		
		RoomBooking modified = dto.convertToRoomBooking();
		
		bookingToModify = modified;
		
		return new RoomBookingDTOOnlyRoom(rbRepo.save(bookingToModify));
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
