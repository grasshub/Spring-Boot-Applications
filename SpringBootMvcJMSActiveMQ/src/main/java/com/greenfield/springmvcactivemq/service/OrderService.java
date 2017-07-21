package com.greenfield.springmvcactivemq.service;

import java.util.List;

import com.greenfield.springmvcactivemq.domain.Order;

public interface OrderService {
	
	public void sendOrder(Order order);
	
	public void updateOrder(Order order);
	
	public List<Order> getAllOrders();
 
}
