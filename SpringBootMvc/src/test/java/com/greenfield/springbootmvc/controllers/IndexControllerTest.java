package com.greenfield.springbootmvc.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class IndexControllerTest {
	
	private static final String INDEX = "index";
	
	private MockMvc mockMvc;
	
	@Before 
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}
	
	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name(INDEX))
		.andDo(print());
	}

}
