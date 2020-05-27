package com.spring_security_exmp.Address_Person.model;

public class AuthenticationResponse {

	private final String jwt;
	
	public AuthenticationResponse(String jwt) { this.jwt = jwt; }

	public String getJwt() { return jwt; }

}
