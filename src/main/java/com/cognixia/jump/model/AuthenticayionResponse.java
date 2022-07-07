package com.cognixia.jump.model;


// response object for when /authentication POST request is called and returns the jwt created
public class AuthenticayionResponse {

	
	private String jwt;
	
	public AuthenticayionResponse (String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}


	
}