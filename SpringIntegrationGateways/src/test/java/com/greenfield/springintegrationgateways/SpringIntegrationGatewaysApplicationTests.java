package com.greenfield.springintegrationgateways;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.greenfield.springintegrationgateways.gateway.ProductGateway;
import com.greenfield.springintegrationgateways.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringIntegrationGatewaysApplication.class)
public class SpringIntegrationGatewaysApplicationTests {
	private static final String ID = "123456";
	private static final String DESCRIPTION = "Product in TESTING!!";
	
	@Autowired
	ProductGateway productGateway;

	@Test
	public void contextLoads() {
		Product product = productGateway.getProduct(ID);
		
		assertNotNull(product);
		assertEquals(product.getProductId(), ID);
		assertEquals(product.getDescription(), DESCRIPTION);
	}

}
