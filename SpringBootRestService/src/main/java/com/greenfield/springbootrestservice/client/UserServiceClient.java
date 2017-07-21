package com.greenfield.springbootrestservice.client;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.greenfield.springbootrestservice.controller.RestUserController;
import com.greenfield.springbootrestservice.model.User;


@Component
public class UserServiceClient {
	
	private static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestService/userService";
	
	public static final Logger logger = LoggerFactory.getLogger(RestUserController.class);
	
	private static RestTemplate restTemplate = new RestTemplate();

	@SuppressWarnings("unchecked")
	private static void findAllUsers() {
		
		logger.info("Testing findAllUsers() service");
		
		List<LinkedHashMap<String, Object>> users = restTemplate.getForObject(REST_SERVICE_URI + "/users", List.class);
		
		if (users != null) 
			for (LinkedHashMap<String, Object> userMap : users) 
				logger.info("User: Id=" + userMap.get("id") + " Name=" + userMap.get("name") + " Age=" + userMap.get("age") + " Salary=" + userMap.get("salary"));
		else
			logger.info("No user exist!");		
	}
	
	private static void getUser() {
		
		logger.info("Testing getUser() service");
		
		User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
		
		logger.info(user.toString());
	}
	
	private static void createUser() {
		
		logger.info("Testing createUser() service");
		
		User user = new User(5, "Sarah", 51, 80000.00);
		
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user", user);
		
		logger.info("Location to access new user: {}" + uri.toASCIIString());
	}
	
	private static void updateUser() {
		 
		logger.info("Testing updateUser() service");
		
		User user = new User(1, "Tomy", 33, 70000.00);
		restTemplate.put(REST_SERVICE_URI + "/user/1", user);
	}
	
	private static void deleteUser() {
		 
		logger.info("Testing deleteUser() service");
		
		restTemplate.delete(REST_SERVICE_URI + "/user/3");
	}
	
	private static void deleteAllUsers() {
		 
		logger.info("Testing deleteAllUsers() service");
		
		restTemplate.delete(REST_SERVICE_URI + "/users");
	}
	
	
	public static void main(String[] args) {
		findAllUsers();	
		getUser();
		createUser();
		findAllUsers();
		updateUser();
		findAllUsers();
		deleteUser();
		findAllUsers();
		deleteAllUsers();
		findAllUsers();
	}

}
