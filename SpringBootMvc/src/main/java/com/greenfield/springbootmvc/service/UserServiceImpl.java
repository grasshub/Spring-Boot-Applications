package com.greenfield.springbootmvc.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenfield.springbootmvc.domain.User;
import com.greenfield.springbootmvc.repositories.UserRepository;
import com.greenfield.springbootmvc.service.security.EncryptionService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EncryptionService encryptionService;

	// In order to initialize list of lazily loaded roles field before transaction is committed and session is closed,
	// we need to utilize Hibernate class initialize method to fetch this list before transaction is committed.
	// This strategy will resolve standalone application issue with accessing lazily loaded list field after session is closed and could
	// not retrieve the list through the closed session.
	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<>();
		// Java 8 feature: method reference add at users object with side effect to populate underline users list
		userRepository.findAll().forEach(users::add);
		
		for ( User user:  users ) {
			Hibernate.initialize(user.getRoles());
		}
		
		return users;
	}

	@Override
	public User getById(Integer id) {
		User user = userRepository.findOne(id);

		// Initialize lazily loaded roles list before the transaction is committed and session is closed automatically.
		Hibernate.initialize(user.getRoles());
		
		return user;
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		// Use the encryptionService to encrypt the password and save it under encryptedPassword field
		if (domainObject.getPassword() != null)
			domainObject.setEncryptedPassword(encryptionService.encryptPassword(domainObject.getPassword()));
		return userRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);	
	}

	@Override
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		
		// Initialize lazily loaded roles list before the transaction is committed and session is closed automatically.
		Hibernate.initialize(user.getRoles());
		
		return user;
	}

}
