package com.generation.javago.model.dto.photo;

import com.generation.javago.model.entity.Photo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericPhotoDTO {
	
	protected int id;
	protected String img_url;
	
	
	public GenericPhotoDTO(){}
	
	public GenericPhotoDTO(Photo p){
		this.id= p.getId(); 
		this.img_url= p.getImg_url(); 
	}
	
	public Photo convertToPhoto(){
		Photo res=new Photo(); 
		res.setId(id);
		res.setImg_url(img_url);
		return res; 
	}
}
