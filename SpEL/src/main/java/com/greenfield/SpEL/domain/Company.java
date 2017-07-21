package com.greenfield.SpEL.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:domain.properties"})
public class Company {
	
	@Autowired
	Environment environment;
	
	@Value("#{environment['business.number']}")
	private long businessNumber;
	
	@Value("#{environment['business.name']}")
	private String name;

	public long getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(long businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [businessNumber=" + businessNumber + ", name=" + name + "]";
	}

}
