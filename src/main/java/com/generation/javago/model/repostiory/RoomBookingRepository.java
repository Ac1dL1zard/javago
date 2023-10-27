package com.generation.javago.model.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.model.entity.RoomBooking;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer>
{

}
