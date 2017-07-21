package com.greenfield.SpEL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.greenfield.SpEL.domain.Company;
import com.greenfield.SpEL.domain.Employee;

@SpringBootApplication
public class SpElApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpElApplication.class, args);
		
		Company company = context.getBean(Company.class);
		Employee employee = context.getBean(Employee.class);
		
		System.out.println(company.toString());
		System.out.println(employee.toString());
	}
}
