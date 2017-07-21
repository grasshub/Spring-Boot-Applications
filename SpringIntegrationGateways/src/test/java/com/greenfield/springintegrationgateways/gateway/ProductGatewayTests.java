package com.greenfield.springintegrationgateways.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.greenfield.springintegrationgateways.SpringIntegrationGatewaysApplication;
import com.greenfield.springintegrationgateways.model.Product;

@RunWith(SpringRunner.class)
//@ContextConfiguration(locations="classpath:/config/test-config.xml")
@ContextConfiguration(classes=SpringIntegrationGatewaysApplication.class)
public class ProductGatewayTests {
	
	private static final String ID = "333333";
	private static final String DESCRIPTION = "Product in TESTING!!";
	private static final String PRODUCTION_ID = "Product Id: ";
	private static final String PRODUCTION_DESCRIPTION = "Product Description: ";
	
	@Autowired
	ProductGateway productGateway;
	
	@Test
	public void getProductTest() {
		Product product = productGateway.getProduct(ID);
		
		assertNotNull(product);
		assertEquals(product.getProductId(), ID);
		assertEquals(product.getDescription(), DESCRIPTION);
		
		System.out.println(PRODUCTION_ID + product.getProductId());
		System.out.println(PRODUCTION_DESCRIPTION + product.getDescription());	
	}

}
