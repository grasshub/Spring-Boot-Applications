package com.greenfield.springbootmvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;

import com.greenfield.springbootmvc.domain.Product;
import com.greenfield.springbootmvc.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=ProductController.class)
@AutoConfigureMockMvc(secure=false)
public class ProductControllerTest {
	
	private static final String PRODUCTS = "products";
	private static final String PRODUCTS_URL = "/products";
	private static final String PRODUCT = "product";
	private static final String SHOW_PRODUCT_URL = "/product/{id}/";
	private static final String PRODUCT_SHOW = "productShow";
	private static final String CONTENT = "text/html;charset=UTF-8";
	private static final String TITLE = "Spring Framework Guru";
	
	private Product product1;
	private static final int ID = 1;
	private static final String DESCRIPTION = "Spring Framework Guru Shirt";
	private static final String IMAGE_URL = "https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg";
	private static final double PRICE = 18.95;
	private static final String PRODUCT_ID = "235268845711068308";
	private static final String ID_FIELD = "id";
	private static final String PRODUCT_FIELD = "productId";
	private static final String DESCRIPTION_FIELD = "description";
	private static final String PRICE_FIELD = "price";
	private static final String URL_FIELD = "imageUrl";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String CHECK = "Check for Content-Type header";
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Autowired
	//private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ProductService productServiceMock;
	
	@Before
	public void setup() {
		//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		product1 = new Product();
		product1.setId(ID);
		product1.setProductId(PRODUCT_ID);
		product1.setDescription(DESCRIPTION);
		product1.setPrice(new BigDecimal(PRICE));
		product1.setImageUrl(IMAGE_URL);
	}
	
	@Test
	public void testListProducts() throws Exception {
		assertThat(productServiceMock).isNotNull();
		
		mockMvc.perform(get(PRODUCTS_URL))
		.andExpect(status().isOk())
		.andExpect(content().contentType(CONTENT))
		.andExpect(view().name(PRODUCTS))
		.andExpect(content().string(containsString(TITLE)))
		.andDo(print());
	}
	
	@Test
	public void testShowProduct() throws Exception {
		assertThat(productServiceMock).isNotNull();
		
		// Stubbing (set the expectation)
		when(productServiceMock.getProductById(ID)).thenReturn(product1);
		
		MvcResult result = mockMvc.perform(get(SHOW_PRODUCT_URL, ID))
						   .andExpect(status().isOk())
						   .andExpect(view().name(PRODUCT_SHOW))
						   .andExpect(model().attributeExists(PRODUCT))
						   .andExpect(model().attribute(PRODUCT, hasProperty(ID_FIELD, is(ID))))
						   .andExpect(model().attribute(PRODUCT, hasProperty(PRODUCT_FIELD, is(PRODUCT_ID))))
						   .andExpect(model().attribute(PRODUCT, hasProperty(DESCRIPTION_FIELD, is(DESCRIPTION))))
						   .andExpect(model().attribute(PRODUCT, hasProperty(PRICE_FIELD, is(new BigDecimal(PRICE)))))
						   .andExpect(model().attribute(PRODUCT, hasProperty(URL_FIELD, is(IMAGE_URL))))
						   .andDo(print())
						   .andReturn();
		
		MockHttpServletResponse mockResponse = result.getResponse();
		assertThat(mockResponse.getContentType()).isEqualTo(CONTENT);
		
		Collection<String> responseHeaders = mockResponse.getHeaderNames();
		assertNotNull(responseHeaders);
		assertEquals(ID, responseHeaders.size());
		assertEquals(CHECK, CONTENT_TYPE, responseHeaders.iterator().next());
		
		String responseString = mockResponse.getContentAsString();
		assertTrue(responseString.contains(TITLE));
		
		verify(productServiceMock).getProductById(ID);
		// Verify that getProductById is the only method call at productServiceMock object
		verifyNoMoreInteractions(productServiceMock);	
	}

}
