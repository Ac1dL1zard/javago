package com.generation.javago.auth.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class UserInDb 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String password;
	
	private boolean employed;
	
	private boolean isUsernameValid()
    {
        if (username == null || username.isBlank())
            return false;

        String emailRegex = "^[A-Za-z0-9+.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }
	
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<String>();
		
		if(!isUsernameValid())
			res.add("Invalid inserted email");
		if(password == null || password.isBlank())
			res.add("Missing value for field 'password'");
		if(!(password==null || password.isBlank()) && password.length()<8)
			res.add("Password must be at least 8 characters long");
		
		return res;
			
	}
	
	public boolean isTheSame(UserInDb userToCheck)
	{
		return 	(	this.getId() == userToCheck.getId())
						&&
				(	this.getUsername().equals(userToCheck.getUsername()))
						&&
				( 	this.getPassword().equals(userToCheck.getPassword()));
			
	}
	
	public boolean isValid()
	{
		if(getErrors().size() == 0)
			return true;
		
		return false;
	}
}
