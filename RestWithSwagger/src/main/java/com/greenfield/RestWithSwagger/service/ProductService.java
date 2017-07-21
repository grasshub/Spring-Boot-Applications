package com.greenfield.RestWithSwagger.service;

import com.greenfield.RestWithSwagger.domain.Product;

public interface ProductService {
	
	public Product getProductById(Integer id);
	
	public Iterable<Product> listAllProducts();
	
	public Product saveProduct(Product product);
	
	public void deleteProduct(Integer id);
	
}

