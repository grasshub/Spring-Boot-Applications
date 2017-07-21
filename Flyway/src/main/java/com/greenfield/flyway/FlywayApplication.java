package com.greenfield.flyway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.greenfield.flyway.domain.EmployeeRepository;
import com.greenfield.flyway.domain.PersonRepository;

@SpringBootApplication
public class FlywayApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(FlywayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlywayApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner employeeRunner(final EmployeeRepository repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				logger.error(repository.findAll().toString());
			}
		};
	}
	
	@Bean
	public CommandLineRunner personRunner(final PersonRepository repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				logger.error(repository.findAll().toString());
			}
		};
	}
	
}
