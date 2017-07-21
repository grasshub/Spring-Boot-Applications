package com.greenfield.springresthateoasservice;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.greenfield.springresthateoasservice.domain.Account;
import com.greenfield.springresthateoasservice.domain.Bookmark;
import com.greenfield.springresthateoasservice.repository.AccountRepository;
import com.greenfield.springresthateoasservice.repository.BookmarkRepository;

@SpringBootApplication
public class SpringRestHateoasServiceApplication extends SpringBootServletInitializer {
	
	private static final String USER_NAMES = "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong";
	private static final String COMMA = ",";
	private static final String PASSWORD = "password";
	private static final String URL = "http://bookmark.com/1/";
	private static final String URL1 = "http://bookmark.com/2/";
	private static final String DESCRIPTION = "First bookmark description";
	private static final String DESCRIPTION1 = "Second bookmark description";

	public static void main(String[] args) {
		SpringApplication.run(SpringRestHateoasServiceApplication.class, args);
	}
	
	// Add this method in order to execute from external web server.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringRestHateoasServiceApplication.class);
	}
	
	// Use the CommandLineRunner to initialize the database with sample data for Account and Bookmarks.
	// Use the Java 8 concise lambda expression to replace the anonymous inner class with run method of CommandLineRunner
	@Bean
	CommandLineRunner initialize(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
		return args ->  Arrays.asList(USER_NAMES.split(COMMA))
							.forEach(
								username -> {	
												Account account = accountRepository.save(new Account(username, PASSWORD));
												bookmarkRepository.save(new Bookmark(account, URL + username, DESCRIPTION));
												bookmarkRepository.save(new Bookmark(account, URL1 + username, DESCRIPTION1));
								});
	}
	
}
