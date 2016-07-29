package com.datasection.facebook.be.entities;

public class UserInfo {
	private String userId;
	private String username;
	private long numCmt;

	public UserInfo(String userId, String username, long numCmt) {
		this.userId = userId;
		this.username = username;
		this.numCmt = numCmt;
	}

	public UserInfo() {
	}

	public long getNumCmt() {
		return numCmt;
	}

	public void setNumCmt(long numCmt) {
		this.numCmt = numCmt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
