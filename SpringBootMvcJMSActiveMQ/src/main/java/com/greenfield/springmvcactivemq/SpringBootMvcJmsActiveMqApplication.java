package com.greenfield.springmvcactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootMvcJmsActiveMqApplication extends SpringBootServletInitializer {

	// Add this method in order to execute from external web server.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootMvcJmsActiveMqApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcJmsActiveMqApplication.class, args);
	}
}
