package com.greenfield.springintegrationgateways.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.greenfield.springintegrationgateways.model.Product;

@MessagingGateway
public interface ProductGateway {
	
	@Gateway(requestChannel="getProductChannel")
	public Product getProduct(String productId);

}
