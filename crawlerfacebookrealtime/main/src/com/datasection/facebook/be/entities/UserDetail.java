package com.datasection.facebook.be.entities;

public class UserDetail {

	private long id;
	private String user_id;
	private String name;
	private long postId;
	private int status;
	private long created_time;

	public UserDetail() {
		super();
	}

	public UserDetail(long id, String user_id, String name, long post, int status, long created_time) {
		super();
		this.id = id;
		this.name = name;
		this.postId = post;
		this.status = status;
		this.user_id = user_id;
		this.created_time = created_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public long getCreated_time() {
		return created_time;
	}

	public void setCreated_time(long created_time) {
		this.created_time = created_time;
	}

	

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
