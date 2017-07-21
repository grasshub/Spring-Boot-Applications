package com.greenfield.springintegrationgateways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ImportResource;

import com.greenfield.springintegrationgateways.gateway.ProductGateway;
import com.greenfield.springintegrationgateways.model.Product;

@SpringBootApplication
//@ImportResource("classpath:/config/application-config.xml")
public class SpringIntegrationGatewaysApplication {
	
	private static final String ID = "123456";
	private static final String PRODUCT_GATEWAY = "productGateway";
	private static final String PRODUCTION_ID = "Product Id: ";
	private static final String PRODUCTION_DESCRIPTION = "Product Description: ";

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringIntegrationGatewaysApplication.class, args);
		
		ProductGateway productGateway = context.getBean(PRODUCT_GATEWAY, ProductGateway.class);
		
		Product product = productGateway.getProduct(ID);
		
		System.out.println(PRODUCTION_ID + product.getProductId());
		System.out.println(PRODUCTION_DESCRIPTION + product.getDescription());	
	}
}
