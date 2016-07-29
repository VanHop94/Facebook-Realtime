package com.datasection.facebook.be.entities;

import java.util.List;

public class ResultInfo {
	private PostDetail post_detail;
	private ActionInfo action_info;
	private List<UserInfo> user_info;
	private List<CommentDetail> comment_detail;

	public ResultInfo() {
	}

	public ResultInfo(PostDetail post_detail, ActionInfo action_info, List<UserInfo> user_info,
			List<CommentDetail> comment_detail) {
		super();
		this.post_detail = post_detail;
		this.action_info = action_info;
		this.user_info = user_info;
		this.comment_detail = comment_detail;
	}

	public PostDetail getPost_detail() {
		return post_detail;
	}

	public void setPost_detail(PostDetail post_detail) {
		this.post_detail = post_detail;
	}

	public ActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(ActionInfo action_info) {
		this.action_info = action_info;
	}

	public List<UserInfo> getUser_info() {
		return user_info;
	}

	public void setUser_info(List<UserInfo> user_info) {
		this.user_info = user_info;
	}

	public List<CommentDetail> getComment_detail() {
		return comment_detail;
	}

	public void setComment_detail(List<CommentDetail> comment_detail) {
		this.comment_detail = comment_detail;
	}



}
