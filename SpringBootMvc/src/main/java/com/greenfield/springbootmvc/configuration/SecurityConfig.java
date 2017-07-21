package com.greenfield.springbootmvc.configuration;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ROOT = "/";
	private static final String PRODUCTS = "/products";
	private static final String PRODUCT_SHOW = "/product/show/*";
	private static final String H2_CONSOLE = "/console/*";
	private static final String LOGIN = "/login";
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder(StrongPasswordEncryptor strongPasswordEncryptor) {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		passwordEncoder.setPasswordEncryptor(strongPasswordEncryptor);
		
		return passwordEncoder;
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	@Autowired
	public void configureAuthenticationManager(AuthenticationManagerBuilder authenticationManager) {
		authenticationManager.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// Permit all users access to root, products, product description, H2 database console, login and logout
		httpSecurity.authorizeRequests().antMatchers(ROOT, PRODUCTS, PRODUCT_SHOW, H2_CONSOLE).permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage(LOGIN).permitAll()
		.and()
		.logout().permitAll();	
			
		// Disable the CSRF
		httpSecurity.csrf().disable();
		// Enable H2 Console running inside frame
		httpSecurity.headers().frameOptions().disable();
	}

}
