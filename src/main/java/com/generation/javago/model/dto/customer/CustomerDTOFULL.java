package com.generation.javago.model.dto.customer;

import java.util.List;

import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.dto.user.GenericUserDTO;
import com.generation.javago.model.entity.Customer;
import com.generation.javago.model.entity.RoomBooking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTOFULL extends GenericCustomerDTO{
	
	private List<RoomBookingGenericDTO> roomBookingsDTO; 
	private GenericUserDTO userDTO; 

	
	public CustomerDTOFULL() {}

	public CustomerDTOFULL(Customer c) {
		super(c); 
		userDTO= new GenericUserDTO(c.getUser()); 
		roomBookingsDTO= c.getBookings().stream().map(book -> new RoomBookingGenericDTO(book)).toList(); 
	}
	
	
	
	@Override
	public Customer convertToCustomer() {
		Customer res= super.convertToCustomer(); 
		
		res.setUser(userDTO.convertToUser());
		
		res.setBookings(roomBookingsDTO.stream().map(roomBookingDTO -> {
												RoomBooking roomBooking= roomBookingDTO.convertToRoomBooking();
												roomBooking.setCustomer(res); 
												return roomBooking; 
												}).toList());
		return res; 
		}
}
