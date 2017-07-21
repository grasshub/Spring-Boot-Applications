package com.greenfield.springbootmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// Add the superclass SpringBootServletInitializer to run as web application at external web server (instead of embedded Tomcat server.)
@SpringBootApplication
public class SpringBootMvcApplication extends SpringBootServletInitializer {

	// Add this method in order to execute from external web server.
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 return application.sources(SpringBootMvcApplication.class);
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcApplication.class, args);
	}
	
}

