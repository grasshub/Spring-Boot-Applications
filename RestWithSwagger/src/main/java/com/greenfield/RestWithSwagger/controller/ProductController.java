package com.greenfield.RestWithSwagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.greenfield.RestWithSwagger.domain.Product;
import com.greenfield.RestWithSwagger.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(description="Operations pertaining to products in Online Store")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value="Viwe a list of available products", response=Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code=200, message="Successfully retrieved product list."),
			@ApiResponse(code=401, message="You are not authorized to view the product list."),
			@ApiResponse(code=403, message="Forbidden to access the product list."),
			@ApiResponse(code=404, message="The product list does not found."),
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json") 
	public Iterable<Product> listProducts() {
		Iterable<Product> products = productService.listAllProducts();
		
		return products;
	}
	
	@ApiOperation(value="Search a product based on ID", response=Product.class)
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces="application/json") 
	public Product showProduct(@PathVariable("id") Integer id) {
		Product product = productService.getProductById(id);
		
		return product;
	}
	
	@ApiOperation(value="Addd a product")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces="application/json") 
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity<String>("Product saved successfully.", HttpStatus.OK);	
	}
	
	@ApiOperation(value="Update a product")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces="application/json") 
	public ResponseEntity<String> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
		Product existingProduct = productService.getProductById(id);
		
		existingProduct.setDescription(product.getDescription());
		existingProduct.setProductId(product.getProductId());
		existingProduct.setImageUrl(product.getImageUrl());
		existingProduct.setPrice(product.getPrice());
		
		productService.saveProduct(existingProduct);
		
		return new ResponseEntity<String>("Product upated successfully.", HttpStatus.OK);	
	}
	
	@ApiOperation(value="Delete a product")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces="application/json") 
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		
		return new ResponseEntity<String>("Product deleted successfully.", HttpStatus.OK);
	}

}