package com.greenfield.RestWithSwagger.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes="The database generated ID as primary key", required=true)
	private Integer id;
	
	@Version
	@ApiModelProperty(notes="The auto-generated version for ensuring the integrity of merge operation and optimistic currency contorl")
	private Integer version;
	
	@ApiModelProperty(notes="The application specific product ID")
	private String productId;
	
	@ApiModelProperty(notes="The product description")
	private String description;
	
	@ApiModelProperty(notes="The image URL of the product")
	private String imageUrl;
	
	@ApiModelProperty(notes="The price of the product", required=true)
	private BigDecimal price;

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
