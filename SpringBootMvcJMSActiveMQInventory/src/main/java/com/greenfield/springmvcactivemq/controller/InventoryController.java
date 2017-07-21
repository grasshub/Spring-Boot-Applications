package com.greenfield.springmvcactivemq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InventoryController {

	private static final String HOME = "home";

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String inventoryHome(Model model) {
		return HOME;
	}

}
