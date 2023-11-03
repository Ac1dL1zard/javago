package com.generation.javago.model.repostiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.model.entity.Room;
import com.generation.javago.model.entity.RoomBooking;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer>
{
	public List<RoomBooking> findByRoom(Room room); 
}
