package com.greenfield.springbootmvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenfield.springbootmvc.helper.SpringLoggingHelper;

@Controller
public class IndexController {
	
	private static final String INDEX = "index";
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		logger.debug("This is a dubug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		
		// Add SpringLoggingHelper class to emit logging statements that will help us understand configuring logging across different packages.
		new SpringLoggingHelper().helpMethod();
		return INDEX;		
	}
}
