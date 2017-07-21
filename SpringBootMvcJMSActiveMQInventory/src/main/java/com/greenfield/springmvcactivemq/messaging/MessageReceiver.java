package com.greenfield.springmvcactivemq.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.greenfield.springmvcactivemq.domain.Order;
import com.greenfield.springmvcactivemq.service.InventoryService;

@Component
public class MessageReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	private static final String ORDER_QUEUE = "order.queue";
	private static final String CONTAINER_FACTORY = "jmsListenerContainerFactory";
	
	@Autowired
	InventoryService inventoryService;
	
	@JmsListener(destination = ORDER_QUEUE, containerFactory = CONTAINER_FACTORY)
	public void receiveMessage(Order order) {
		logger.info("Receiving the message from order queue");
		
		inventoryService.processOrder(order);
		
		logger.info("Message receiver: order received: {}", order);
	}
	
}
