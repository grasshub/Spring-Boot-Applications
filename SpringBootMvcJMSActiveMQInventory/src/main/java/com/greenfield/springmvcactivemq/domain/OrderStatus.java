package com.greenfield.springmvcactivemq.domain;

public enum OrderStatus {
	
	CREATED("Created"),
	CONFIRMED("Confirmed");
	
	private String status;
	
	private OrderStatus(final String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return status;
	}
	
}
