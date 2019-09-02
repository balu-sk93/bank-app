package com.bank.app.service.model;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String jwttoken;

	public AuthResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}
