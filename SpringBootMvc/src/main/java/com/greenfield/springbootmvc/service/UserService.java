package com.greenfield.springbootmvc.service;

import com.greenfield.springbootmvc.domain.User;

public interface UserService extends CRUDService<User> {
	
	public User findByUsername(String username);

}
