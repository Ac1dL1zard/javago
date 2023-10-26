package com.generation.javago.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.generation.javago.model.library.BaseEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity
{

	private String email; 
	private String password; 
	private Integer level; 
	private String img_url; 
	
	
	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<String>(); 
		
		if (email==null || _isEmailValid()==false)
			errors.add("Missing or invalid value for field 'email\""); 
		if (password==null)
			errors.add("Missing or invalid value for field 'password\"");
		if (level==null)
			errors.add("Missing or invalid value for field 'level\"");
		
		return errors;
	}
	
	private boolean _isEmailValid()
	{
        if (email == null || email.isBlank())
        	return false;

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
