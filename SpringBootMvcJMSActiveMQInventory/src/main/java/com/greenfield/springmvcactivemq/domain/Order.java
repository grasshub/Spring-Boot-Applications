package com.greenfield.springmvcactivemq.domain;

import java.io.Serializable;

// Implements Serializable in order to be converted by default MessageConverter from Spring Boot to JMS message
public class Order implements Serializable {
	
	private static final long serialVersionUID = 2344015152940932522L;

	private Integer id;
	
	private Integer version;
	
	private String productName;
	
	private Integer quantity;
	
	private OrderStatus orderStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Override
	public String toString() {
		return "Order[orderId=" + id + ", productName=" + productName + ", quantity=" + quantity + ", orderStatus=" + orderStatus + "]";
    }

}
