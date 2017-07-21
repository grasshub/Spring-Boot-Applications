package com.greenfield.springresthateoasservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bookmark {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private Account account;
	
	private String uri;
	private String description;
	
	public Bookmark(Account account, String uri, String description) {
        this.uri = uri;
        this.description = description;
        this.account = account;
    }
	
	public Bookmark(String uri, String description) {
        this.uri = uri;
        this.description = description;
    }
	
	// JPA only
	public Bookmark() {}
	
	public Long getId() {
		return id;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "Bookmark [id=" + id + ", account=" + account + ", uri=" + uri + ", description=" + description + "]";
	}

}
