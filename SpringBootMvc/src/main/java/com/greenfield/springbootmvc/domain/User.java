package com.greenfield.springbootmvc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class User extends AbstractDomainClass {
	
	private String username;
	
	@Transient
	private String password;
	
	private String encryptedPassword;
	private Boolean enabled = true;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Role> roles = new ArrayList<>();
	
	private Integer failedLoginAttempts = 0;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	//Helper method for adding or removing child entities at owning side of ManyToMany relation
	public void addRole(Role role) {
		if (!roles.contains(role))
			roles.add(role);
		
		// Update the data at other side of association
		if (!role.getUsers().contains(this))
			role.getUsers().add(this);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}
 
	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

}
