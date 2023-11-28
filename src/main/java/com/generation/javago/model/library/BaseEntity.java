package com.generation.javago.model.library;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	public abstract List<String> getErrors();
	
	public boolean isValid()
	{
		return getErrors().isEmpty();
	}
}
