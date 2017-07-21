package com.greenfield.springbootmvc.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.greenfield.springbootmvc.domain.User;
import com.greenfield.springbootmvc.service.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private Converter<User, UserDetails> userToUserDetailsConverter;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		return userToUserDetailsConverter.convert(userService.findByUsername(username));
	}
	
}
