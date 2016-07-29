package com.datasection.facebook.be.dao;

import java.util.List;

import com.datasection.facebook.be.entities.PostDetail;

public interface PostDao{

	public boolean addListPosts(List<PostDetail> list);
	public boolean addPost(PostDetail post);
	public PostDetail queryPost(String id, String userId);
	public List<PostDetail> queryAllPost(String user_id);
	public List<PostDetail> queryAllPostIsRunning();
	public boolean updatePost(PostDetail post);
	public boolean updateTimeUtil(String postId, long date_time);
	public boolean updateTimeUpdate(String postId, long date_time);
	public void deletePost(String postId, String userId);
	public int getAllPostIsActive(String user_id);
	//
	//public List<PostDetail> queryPostIsUnread();
	//public boolean updateStatus(String postId, int status);
}
