package com.greenfield.springbootrestservice.util;

public class UserError {
	
	private String errorMessage;
	
	public UserError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
