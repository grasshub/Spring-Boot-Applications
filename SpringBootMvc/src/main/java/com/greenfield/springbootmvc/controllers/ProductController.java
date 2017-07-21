package com.greenfield.springbootmvc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenfield.springbootmvc.domain.Product;
import com.greenfield.springbootmvc.service.ProductService;

@Controller
public class ProductController {
	
	private static final String PRODUCT = "product";
	private static final String PRODUCTS = "products";
	private static final String PRODUCT_FORM = "productForm";
	private static final String REDIRECT_PRODUCT = "redirect:product/";
	private static final String REDIRECT_PRODUCTS = "redirect:/products";
	private static final String PRODUCT_SHOW = "productShow";
	private static final String LOGIN = "login";
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "product/add", method = RequestMethod.GET) 
	public String addProduct(Model model) {
		// Return an empty Product to client to avoid null pointer at form fields
		model.addAttribute(PRODUCT, new Product());
		return PRODUCT_FORM;	
	}
	
	@RequestMapping(value = "product", method = RequestMethod.POST) 
	public String saveProduct(@Valid Product product, BindingResult result) {
		
		if (result.hasErrors()) {
			return PRODUCT_FORM;
		}
		
		productService.saveProduct(product);
		return REDIRECT_PRODUCT + product.getId();	
	}
	
	@RequestMapping(value = "product/{id}", method = RequestMethod.GET) 
	public String showProduct(@PathVariable("id") Integer id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute(PRODUCT, product);
		
		return PRODUCT_SHOW;
	}
	
	@RequestMapping(value = "products", method = RequestMethod.GET) 
	public String listProducts(Model model) {
		Iterable<Product> products = productService.listAllProducts();
		model.addAttribute(PRODUCTS, products);
		
		return PRODUCTS;
	}
	
	@RequestMapping(value = "product/edit/{id}", method = RequestMethod.GET) 
	public String updateProduct(@PathVariable("id") Integer id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute(PRODUCT, product);
		
		return PRODUCT_FORM;
	}
	
	@RequestMapping(value = "product/delete/{id}", method = RequestMethod.GET) 
	public String deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		
		// Since this redirect is from GET/REDIRECT/GET instead of POST/REDIRECT/GET, you have to use redirect:/products
		// instead of redirect:products, otherwise it will redirect to relative path /product/delete/products instead
		// of /products you wanted
		return REDIRECT_PRODUCTS;
	}
	
	// Login page
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String login() {
		
		return LOGIN;
	}

}
