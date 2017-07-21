package com.greenfield.springresthateoasservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greenfield.springresthateoasservice.repository.AccountRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return  new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				return accountRepository.findByUsername(username)
						.map( account -> {
							return new User(account.getUsername(), account.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER", "read", "write"));
						})
						.orElseThrow( () -> new UsernameNotFoundException(username) );
			}
		};
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		return daoAuthenticationProvider;
	}
		
	@Autowired
	public void configureAuthenticationManager(AuthenticationManagerBuilder authenticationManager) throws Exception {
		authenticationManager.userDetailsService(userDetailsService());
		authenticationManager.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// Permit all users access to root, H2 database console and retrieval of access token of OAuth2 protocol
		httpSecurity.anonymous().disable().authorizeRequests().antMatchers("/", "/oauth/token", "/console/*").permitAll();
		
		// Disable the CSRF
		httpSecurity.csrf().disable();
		// Enable H2 Console running inside frame
		httpSecurity.headers().frameOptions().disable();		
	}

}
