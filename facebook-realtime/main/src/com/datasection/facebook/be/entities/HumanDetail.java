package com.datasection.facebook.be.entities;

public class HumanDetail {

	private String username;
	private String password;
	private int rule;

	public HumanDetail() {
		super();
	}

	public HumanDetail(String username, String password, int rule) {
		super();
		this.username = username;
		this.password = password;
		this.rule = rule;
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

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

}
