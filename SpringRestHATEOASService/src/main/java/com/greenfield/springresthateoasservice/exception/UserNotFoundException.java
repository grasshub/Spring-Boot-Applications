package com.greenfield.springresthateoasservice.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 5857375491421567012L;

	public UserNotFoundException(String username) {
		super("Could not find user " + username);
	}

}
