package com.eas.entity;

public class UserLogin {
	private int userId;
	private String password;
	
	public UserLogin() {
		// TODO Auto-generated constructor stub
	}

	public UserLogin(int userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
