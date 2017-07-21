package com.greenfield.springbootmvc.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greenfield.springbootmvc.configuration.RepositoryConfig;
import com.greenfield.springbootmvc.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
public class ProductRepositoryTest {
	
	private static final String DESCRIPTION = "Product Description";
	private static final String NEW_DESCRIPTION = "New Product Description";
	private static final double PRICE = 15.50;
	private static final String URL = "http://www.google.com";
	private static final String ID = "12345";
	private static final int ONE = 1;
	
	@Autowired
	private ProductRepository productRepository;
	
	@SuppressWarnings("unused")
	@Test
	public void testSaveProduct() {
		
		// Create a new product
		Product product = new Product();
		product.setDescription(DESCRIPTION);
		product.setPrice(new BigDecimal(PRICE));
		product.setProductId(ID);
		product.setImageUrl(URL);
		
		// Save the product, verify it has ID after saving
		assertNull(product.getId()); // null before saving
		
		productRepository.save(product);
		
		assertNotNull(product.getId()); // not null after saving
		
		// Retrieve from database
		Product retrievedProduct = productRepository.findOne(product.getId());
		
		assertNotNull(retrievedProduct);
		
		// Product Id should be same
		assertEquals(product.getId(), retrievedProduct.getId());
		assertEquals(product.getDescription(), retrievedProduct.getDescription());
		
		// Update the description and save
		retrievedProduct.setDescription(NEW_DESCRIPTION);
		productRepository.save(retrievedProduct);
		
		// Retrieve from database again
		Product retrievedUpdateProduct = productRepository.findOne(retrievedProduct.getId());
		assertEquals(retrievedProduct.getDescription(), retrievedUpdateProduct.getDescription());
		
		// Verify number of products in database
		long productNumber = productRepository.count();
		assertEquals(productNumber, ONE);
		
		// Get all products
		Iterable<Product> products = productRepository.findAll();
		
		int count = 0;
		
		for (Product product1 : products) {
			count++;
		}
		
		assertEquals(count, ONE);	
	}

}
