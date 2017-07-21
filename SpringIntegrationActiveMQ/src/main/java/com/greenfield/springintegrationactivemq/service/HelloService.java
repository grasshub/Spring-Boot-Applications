package com.greenfield.springintegrationactivemq.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	@ServiceActivator(inputChannel="helloChannel")
	public void sayHello(String name) {
		
		 System.out.println("################################");
	     System.out.println("################################");
	     System.out.println("################################");
	     System.out.println("##  Hello " + name + "!!!" );
	     System.out.println("################################");
	     System.out.println("################################");
	     System.out.println("################################");
	}

}
