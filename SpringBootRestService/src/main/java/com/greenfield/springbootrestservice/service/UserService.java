package com.greenfield.springbootrestservice.service;

import java.util.List;

import com.greenfield.springbootrestservice.model.User;

public interface UserService {
	
	public User findById(long id);
	
	public User findByName(String name);
	
	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public void deleteById(long id);

	public List<User> findAllUsers();
	
	public void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
}
