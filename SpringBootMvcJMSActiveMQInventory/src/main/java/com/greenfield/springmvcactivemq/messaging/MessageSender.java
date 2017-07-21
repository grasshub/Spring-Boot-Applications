package com.greenfield.springmvcactivemq.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.greenfield.springmvcactivemq.domain.Order;

@Component
public class MessageSender {
	
	// Injecting the TmsTemplate automatically created by Spring Boot
	@Autowired
	JmsTemplate jmsTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	private static final String ORDER_RESPONSE_QUEUE = "order-response.queue";
	
	public void sendMessage(final Order order) {
		logger.info("Sending the message to order reponse queue");
		
		jmsTemplate.convertAndSend(ORDER_RESPONSE_QUEUE, order);
		
		logger.info("Message sender: order reponse sent for: {}", order);
	}

}
