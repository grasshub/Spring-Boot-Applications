package com.greenfield.springintegrationgateways.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import com.greenfield.springintegrationgateways.model.Product;

@Service
public class TestGatewayService {

	private String lastString;

	private static final String DESCRIPTION = "Product in TESTING!!";

	// Annotation for the Messaging response handler
	@ServiceActivator(inputChannel="getProductChannel")
	public Product getProduct(String productId) {
		Product product = new Product();
		product.setProductId(productId);
		product.setDescription(DESCRIPTION);
		return product;
	}

	public String getLastString() {
		return lastString;
	}

	public void setLastString(String lastString) {
		this.lastString = lastString;
	}

}
