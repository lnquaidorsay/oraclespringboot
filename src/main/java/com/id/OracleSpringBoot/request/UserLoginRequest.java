package com.id.OracleSpringBoot.request;

public class UserLoginRequest {
	private String email;

	private String password;

	public UserLoginRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
