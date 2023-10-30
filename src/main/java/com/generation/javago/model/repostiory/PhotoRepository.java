package com.generation.javago.model.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javago.model.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

}
