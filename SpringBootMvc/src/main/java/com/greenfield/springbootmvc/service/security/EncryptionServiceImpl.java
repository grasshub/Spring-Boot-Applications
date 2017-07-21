package com.greenfield.springbootmvc.service.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
	
	@Autowired
	private StrongPasswordEncryptor strongPasswordEncryptor;

	@Override
	public String encryptPassword(String password) {
		return strongPasswordEncryptor.encryptPassword(password);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return strongPasswordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

}
