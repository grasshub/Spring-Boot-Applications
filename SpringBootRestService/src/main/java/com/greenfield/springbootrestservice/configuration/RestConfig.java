package com.greenfield.springbootrestservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.jamonapi.JAMonFilter;

@Configuration
public class RestConfig {
	
	private static final String JAMON_FILTER = "JAMonFilter";
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	// Add the JAMON servlet filter (So where any web resource is accessed (like Servlets, JSPs, Images and HTMLs), statistics will be collected.
	@Bean
	public FilterRegistrationBean jamonServletFilterRegistration() {
		
		FilterRegistrationBean jamonServletFilterRegistration = new FilterRegistrationBean();
		jamonServletFilterRegistration.setFilter(new JAMonFilter());
		jamonServletFilterRegistration.setName(JAMON_FILTER);
		jamonServletFilterRegistration.addUrlPatterns("/*");
		jamonServletFilterRegistration.setOrder(1);
		
		return jamonServletFilterRegistration;
	}

}
