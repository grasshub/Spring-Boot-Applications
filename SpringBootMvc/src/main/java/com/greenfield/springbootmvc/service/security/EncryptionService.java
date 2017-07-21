package com.greenfield.springbootmvc.service.security;

public interface EncryptionService {
	
	public String encryptPassword(String password);
	
	public boolean checkPassword(String plainPassword, String encryptedPassword);

}
