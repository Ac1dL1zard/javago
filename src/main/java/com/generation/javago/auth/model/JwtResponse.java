package com.generation.javago.auth.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Integer userid;
	private final boolean employed;

	public JwtResponse(String jwttoken, Integer userid, boolean employed) {
		this.jwttoken = jwttoken;
		this.userid = userid;
		this.employed = employed;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public Integer getUserId() {
		return this.userid;
	}
	
	public boolean isEmployed()
	{
		return this.employed ;
	}
}