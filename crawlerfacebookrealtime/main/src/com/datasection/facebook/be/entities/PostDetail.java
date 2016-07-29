package com.datasection.facebook.be.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.datasection.facebook.utils.StringUtil;

public class PostDetail {

	private long id;
	private String postId;
	private String pageId;
	private String message;
	private long update_time;
	private long update_util;
	private long like_count;
	private long comment_count;
	private long share_count;
	private int status;
	private long created_time;
	private String managerId;
	private String picture;
	private HumanDetail human;
	private long begin_time;
	private HashMap<String, CommentDetail> listComments;
	private HashMap<String, UserDetail> listUsers;
	private ArrayList<UserDetail> userInsertToDatabase;
	private ArrayList<CommentDetail> commentInsertToDatabase;

	public PostDetail() {
		super();
	}

	public PostDetail(PostDetail postDetail) {
		this.id = postDetail.getId();
		this.postId = postDetail.getPostId();
		this.like_count = postDetail.getLike_count();
		this.share_count = postDetail.getShare_count();
		this.comment_count = postDetail.getComment_count();
	}

	public PostDetail(long id, String postId, String pageId, String message, long update_util, long like_count, long comment_count,
			long share_count, long update_time, String picture, long created_time, HumanDetail user, int status,
			long begin_time,String managerId) {
		super();
		this.id = id;
		this.postId = postId;
		this.pageId = pageId;
		this.update_time = update_time;
		this.update_util = update_util;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.share_count = share_count;
		this.message = StringUtil.escapeNonUnicode(message);
		this.picture = picture;
		this.created_time = created_time;
		this.human = user;
		this.status = status;
		this.begin_time = begin_time;
		this.managerId = managerId;
		listComments = new HashMap<String, CommentDetail>();
		listUsers = new HashMap<String, UserDetail>();
		userInsertToDatabase = new ArrayList<UserDetail>();
		commentInsertToDatabase = new ArrayList<CommentDetail>();
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public long getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(long begin_time) {
		this.begin_time = begin_time;
	}

	public long getCreated_time() {
		return created_time;
	}

	public void setCreated_time(long created_time) {
		this.created_time = created_time;
	}

	public HumanDetail getHuman() {
		return human;
	}

	public void setHuman(HumanDetail human) {
		this.human = human;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

	public long getUpdate_util() {
		return update_util;
	}

	public void setUpdate_util(long update_util) {
		this.update_util = update_util;
	}

	public long getLike_count() {
		return like_count;
	}

	public void setLike_count(long like_count) {
		this.like_count = like_count;
	}

	public long getComment_count() {
		return comment_count;
	}

	public void setComment_count(long comment_count) {
		this.comment_count = comment_count;
	}

	public long getShare_count() {
		return share_count;
	}

	public void setShare_count(long share_count) {
		this.share_count = share_count;
	}

	public ArrayList<CommentDetail> getCommentInsertToDatabase() {
		return commentInsertToDatabase;
	}

	public void addUsers(List<UserDetail> users) {
		userInsertToDatabase = new ArrayList<UserDetail>();
		for (UserDetail user : users) {
			//if (listUsers.get(user.getUser_id()) == null) {
				//listUsers.put(user.getUser_id(), user);
				userInsertToDatabase.add(user);
			//}
		}
	}

	public ArrayList<UserDetail> getUserInsertToDatabase() {
		return userInsertToDatabase;
	}

	public void addComments(List<CommentDetail> comments) {
		commentInsertToDatabase = new ArrayList<CommentDetail>();
		for (CommentDetail comment : comments) {
		//	if (listComments.get(comment.getComment_id()) == null || listComments.get(comment.getComment_id()).getLike_count() != comment.getLike_count()) {
				//listComments.put(comment.getComment_id(), comment);
				commentInsertToDatabase.add(comment);
			//}
			// commentInsertToDatabase.add(comment);
		}
	}


	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String status) {
		this.message = StringUtil.escapeNonUnicode(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
