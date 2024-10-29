package com.intern.model;

public class users {

	private int user_id;
	private String username;
	private String Password;
	private String role;

	public users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public users(int user_id, String username, String password, String role) {
		super();
		this.user_id = user_id;
		this.username = username;
		Password = password;
		this.role = role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
