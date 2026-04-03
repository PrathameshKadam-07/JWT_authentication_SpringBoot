package com.entity;

import jakarta.persistence.Entity;

@Entity
public class AuthBean {

	private String username;
	private String password;
	
	
	
	public AuthBean() {
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
	
	
	
}
