package com.greenfield.springmvcactivemq.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenfield.springmvcactivemq.domain.Order;
import com.greenfield.springmvcactivemq.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	private static final String ORDER_HOME = "home";
	private static final String ORDER = "order";
	private static final String ORDERS = "orders";
	private static final String ORDER_SUCCESS = "orderSuccess";
	private static final String ORDER_STATUS = "orderStatus";
	private static final String SUCCESS = "success";

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String orderHome(Model model) {
		return ORDER_HOME;
	}

	@RequestMapping(value = { "/newOrder" }, method = RequestMethod.GET)
	public String newOrder(Model model) {
		
		Order order = new Order();
		model.addAttribute(ORDER, order);
		return ORDER;
	}

	@RequestMapping(value = "/newOrder", method = RequestMethod.POST)
	public String sendOrder(@Valid Order order, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return ORDER;
		}
		
		orderService.sendOrder(order);
		model.addAttribute(SUCCESS, "Order for " + order.getProductName() + " registered.");
		return ORDER_SUCCESS;
	}

	@RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
	public String checkOrderStatus(Model model) {
		
		model.addAttribute(ORDERS, orderService.getAllOrders());
		return ORDER_STATUS;
	}

}
