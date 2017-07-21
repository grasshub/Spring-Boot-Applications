package com.greenfield.RestWithSwagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	private final String CONTROLLER_PACKAGE ="com.greenfield.RestWithSwagger.controller";
	private final String PRODUCT_PATH = "/product.*";
	
	private static final String TITLE = "Spring Boot REST API";
	private static final String DESCRIPTION = "Spring Boot REST API for Online Store";
	private static final String VERSION = "1.0";
	private static final String TERMS_OF_SERVICS = "Terms of service";
	private final Contact CONTACT = new Contact("John Doe", "http://google.com", "john.doe@gmail.com");
	private static final String LICENSE = "Apache License Version 2.0";
	private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(TITLE, DESCRIPTION, VERSION, TERMS_OF_SERVICS, CONTACT, LICENSE, LICENSE_URL);
				
		return apiInfo;
	}
	
	@Bean
	public Docket productService() {
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(CONTROLLER_PACKAGE))
				.paths(regex(PRODUCT_PATH))
				.build()
				.apiInfo(apiInfo());
	}

}

