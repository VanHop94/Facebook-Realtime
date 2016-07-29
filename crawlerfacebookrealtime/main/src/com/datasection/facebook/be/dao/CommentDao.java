package com.datasection.facebook.be.dao;

import java.util.List;

import com.datasection.facebook.be.entities.CommentDetail;

public interface CommentDao{

	public boolean addComment(CommentDetail comment);
	public boolean addListComments(List<CommentDetail> listComments);
	public List<CommentDetail> queryListComments(String idPost, String userId);
	public CommentDetail queryComment(String id);
	public boolean updateComments(String [] ids, int status, String post_id, String managerId);
	public boolean updateCommentsOfUser(String [] user_ids, String post_id, int status, String managerId);
	public void updateAllCommentsOnPost(String post_id, int status);
	public List<CommentDetail> queryListCommentsIsView(String idPost, String userId);
	
}
