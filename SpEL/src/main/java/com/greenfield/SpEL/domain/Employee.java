package com.greenfield.SpEL.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:domain.properties"})
public class Employee {
	
	@Value("${employee.name}")
	private String name;
	
	@Autowired
	private Company company;
	
	@Value("${employee.department.name}")
	private String departmentName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", company=" + company + ", departmentName=" + departmentName + "]";
	}
	
}
