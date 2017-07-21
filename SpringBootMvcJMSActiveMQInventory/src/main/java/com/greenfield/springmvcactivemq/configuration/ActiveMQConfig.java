package com.greenfield.springmvcactivemq.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {
	
	private static final String BROKER_URL = "tcp://localhost:61616";
    
    private static final String ORDER_RESPONSE_QUEUE = "order-response.queue";
	
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
    	
    	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    	
    	connectionFactory.setBrokerURL(BROKER_URL);
    	connectionFactory.setTrustAllPackages(true);
    	
    	return connectionFactory;
    }
    
    @Bean
    public JmsTemplate jmsTemplate() {
    	
    	JmsTemplate template = new JmsTemplate();
    	
    	template.setConnectionFactory(connectionFactory());
    	template.setDefaultDestinationName(ORDER_RESPONSE_QUEUE);
    	
    	return template;	
    }
    
}
