package com.greenfield.springmvcactivemq.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenfield.springmvcactivemq.domain.Order;
import com.greenfield.springmvcactivemq.domain.OrderStatus;
import com.greenfield.springmvcactivemq.messaging.MessageReceiver;
import com.greenfield.springmvcactivemq.messaging.MessageSender;
import com.greenfield.springmvcactivemq.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public void sendOrder(Order order) {
		// Set the order status to CREATED
		order.setOrderStatus(OrderStatus.CREATED);
		
		orderRepository.save(order);
		
		logger.info("Order {} has been saved to database", order);
		
		messageSender.sendMessage(order);
		
		logger.info("Send a message to order queue for further processing");
	}

	@Override
	public void updateOrder(Order order) {
		
		// Set the order status to CONFIRMED and save it to database
		order.setOrderStatus(OrderStatus.CONFIRMED);
		orderRepository.save(order);
		
		logger.info("Update the Order status to CONFIRMED for: {}", order);		
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		// Java 8 feature to use reference to method call on Collection in order to populate it.
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}

}
