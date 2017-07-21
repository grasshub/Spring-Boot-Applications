package com.greenfield.springbootmvc.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This is the configuration for servlet wrapper of H2 database console and mapping to path of console
@Configuration
public class H2WebConfig {
	
	private static final String PATH = "/console/*";
	
	@Bean
	ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings(PATH);
		
		return registrationBean;
	}
	
}
