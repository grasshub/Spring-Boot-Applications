package com.greenfield.springmvcactivemq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenfield.springmvcactivemq.domain.Order;
import com.greenfield.springmvcactivemq.messaging.MessageReceiver;
import com.greenfield.springmvcactivemq.messaging.MessageSender;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Autowired
	MessageSender messageSender;

	@Override
	public void processOrder(Order order) {

		logger.info("Order {} has been processed successfully", order);
		
		messageSender.sendMessage(order);
		
		logger.info("Send a message to order-response queue for confirmation");
	}

}
