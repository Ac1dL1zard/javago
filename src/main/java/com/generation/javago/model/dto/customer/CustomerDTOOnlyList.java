package com.generation.javago.model.dto.customer;

import java.util.List;

import com.generation.javago.model.dto.roombooking.RoomBookingGenericDTO;
import com.generation.javago.model.entity.Customer;
import com.generation.javago.model.entity.RoomBooking;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CustomerDTOOnlyList extends GenericCustomerDTO{

	
	private List<RoomBookingGenericDTO> roomBookingsDTO; 
	
	public CustomerDTOOnlyList() {}
	
	public CustomerDTOOnlyList(Customer c) {
		super(c); 
		
		roomBookingsDTO= c.getBookings().stream().map(book -> new RoomBookingGenericDTO(book)).toList(); 
	}
	
	@Override
	public Customer convertToCustomer() {
		Customer res= super.convertToCustomer(); 
		res.setBookings(roomBookingsDTO.stream().map(roomBookingDTO -> {
												RoomBooking roomBooking= roomBookingDTO.convertToRoomBooking();
												roomBooking.setCustomer(res); 
												return roomBooking; 
												}).toList());
		return res; 
		}
}
