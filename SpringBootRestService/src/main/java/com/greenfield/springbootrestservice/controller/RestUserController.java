package com.greenfield.springbootrestservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.greenfield.springbootrestservice.model.User;
import com.greenfield.springbootrestservice.service.UserService;
import com.greenfield.springbootrestservice.util.UserError;

@RestController
@RequestMapping("/userService")
public class RestUserController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestUserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/users", method=RequestMethod.GET) 
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		
		if(users == null || users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET) 
	public ResponseEntity<?> getUser(@PathVariable("id") long id) { 
		logger.info("Retrieving user with id {}", id);
		
		User user = userService.findById(id);
		
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new UserError("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);	
	}
	
	// Create a user
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user", method=RequestMethod.POST) 
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder uriBuilder) { 
		logger.info("Creating a user: {}", user);
		
		if (userService.isUserExist(user)) {
			logger.error("Unable to create user. A user with name {} already exist.", user.getName());
			return new ResponseEntity(new UserError("Unable to create uer. A user with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		
		userService.saveUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/userService/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}
	
	// Update a user
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT) 
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating user with id {}", id);
		
		User existingUser = userService.findById(id);
		
		if (existingUser == null) {
			logger.error("Unable to update user. User with id {} not found.", id);
			return new ResponseEntity(new UserError("Unable to update user. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		existingUser.setName(user.getName());
		existingUser.setAge(user.getAge());
		existingUser.setSalary(user.getSalary());
		
		userService.updateUser(existingUser);
		return new ResponseEntity<>(existingUser, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Deleting user with id {}", id);
		
		User user = userService.findById(id);
		
		if (user == null) {
			logger.error("Unable to delete user. User with id {} not found.", id);
			return new ResponseEntity(new UserError("Unable to delete user. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// Delete all users
	@RequestMapping(value="/users", method=RequestMethod.DELETE) 
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting all users.");
		
		userService.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
