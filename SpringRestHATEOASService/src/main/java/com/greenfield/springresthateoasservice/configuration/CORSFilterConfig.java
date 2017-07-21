package com.greenfield.springresthateoasservice.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class CORSFilterConfig {
	
	// Add the CORS Servlet Filter configuration
	@Bean
	public FilterRegistrationBean CORSFilter(@Value("${cors.origin:http://localhost:9000}") String origin) {
		return new FilterRegistrationBean( new Filter() {
			
			@Override
			public void init(FilterConfig filterConfig) throws ServletException {	
				// Keep the default behaviors
			}
			
			@Override
			public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
					throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				String method = request.getMethod();
				
				// Add the CORS header in response object
				response.setHeader("Access-Control-Allow-Origin", origin);
				response.setHeader("Access-Control-Allow-Methods", "POST,PUT,GET,OPTIONS,DELETE");
				response.setHeader("Access-Control-Max-Age", Long.toString(60L * 60));
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader(
						"Access-Control-Allow-Headers",
						"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");	
				
				if ( "OPTIONS".equals(method) )
					response.setStatus(HttpStatus.OK.value());
				else
					chain.doFilter(request, response);
			}
			
			@Override
			public void destroy() {
				// Keep the default behaviors
			}
		});
		
	}

}
