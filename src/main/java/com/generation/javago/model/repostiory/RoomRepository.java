package com.generation.javago.model.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>
{

}
