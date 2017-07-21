package com.greenfield.springmvcactivemq.service;

import com.greenfield.springmvcactivemq.domain.Order;

public interface InventoryService {
	
	public void processOrder(Order order);
	
}
