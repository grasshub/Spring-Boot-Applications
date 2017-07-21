package com.greenfield.springintegrationactivemq;
		
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("classpath:/config/Integration-activemq.xml")
public class SpringIntegrationActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationActiveMqApplication.class, args);
	}
}
