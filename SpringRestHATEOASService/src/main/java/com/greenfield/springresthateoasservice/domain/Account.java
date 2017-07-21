package com.greenfield.springresthateoasservice.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy="account")
	private Set<Bookmark> bookmarks = new HashSet<>();
	
	private String username;
	
	// Ignore by introspection of serialization and deserialization functionality
	@JsonIgnore
	private String password;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// JPA only
	public Account() {}

	public Long getId() {
		return id;
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Set<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

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
	
	public String toString() {
		return "Account [id=" + id + ", username=" + username + "]";
	}

}
