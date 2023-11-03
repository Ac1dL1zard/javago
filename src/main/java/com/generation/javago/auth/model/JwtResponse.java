package com.generation.javago.auth.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Integer userid;

	public JwtResponse(String jwttoken, Integer userid) {
		this.jwttoken = jwttoken;
		this.userid = userid;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public Integer getUserId() {
		return this.userid;
	}
}