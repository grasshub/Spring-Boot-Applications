package com.greenfield.springbootmvc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Role extends AbstractDomainClass {
	
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}	

}
