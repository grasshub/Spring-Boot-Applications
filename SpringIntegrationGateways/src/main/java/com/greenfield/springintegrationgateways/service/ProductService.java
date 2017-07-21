package com.greenfield.springintegrationgateways.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import com.greenfield.springintegrationgateways.model.Product;

@Service
public class ProductService {
	
	private static final String DESCRIPTION = "Product from Production Line";
	
	// Annotation for the Messaging response handler
	@ServiceActivator(inputChannel="getProductChannel")
	public Product getProduct(String productId) {
		Product product = new Product();
		
		product.setProductId(productId);
		product.setDescription(DESCRIPTION);
		
		return product;
	}

}
