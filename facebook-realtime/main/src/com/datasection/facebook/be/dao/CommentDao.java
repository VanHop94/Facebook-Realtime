package com.datasection.facebook.be.dao;

import java.util.List;

import com.datasection.facebook.be.entities.CommentDetail;

public interface CommentDao{

	public boolean addComment(CommentDetail comment);
	public boolean addListComments(List<CommentDetail> listComments);
	public List<CommentDetail> queryListComments(long idPost, String userId);
	public List<CommentDetail> queryListComments(long idPost, String userId, String keyWord);
	public CommentDetail queryComment(String id);
	public boolean updateComments(String [] ids, int status, long post_id, String managerId);
	public boolean updateCommentsOfUser(String [] user_ids, long post_id, int status, String managerId);
	public void updateAllCommentsOnPost(long post_id, int status);
	public List<CommentDetail> queryListCommentsIsView(long idPost, String userId);
	
}
