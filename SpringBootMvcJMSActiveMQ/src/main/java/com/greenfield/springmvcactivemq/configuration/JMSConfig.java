package com.greenfield.springmvcactivemq.configuration;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class JMSConfig {
	
	// Define the DefaultJmsListenerContainerFactory associated with message receiver to allow asynchronously receiving the message from queue
	// JmsTemplate could only synchronously receiving message from queue which is not recommended.
	@Bean
	DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, 
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}
	
}
