package com.datasection.facebook.be.dao;

import java.util.List;

import com.datasection.facebook.be.entities.UserDetail;

public interface UserDao {
	public boolean addListUser(List<UserDetail> list);

	public boolean addUser(UserDetail user);

	public UserDetail queryUser(String id);

	public List<UserDetail> queryListUser(long idPost,long created_time, String userId);
	
	public boolean updateUsers(String [] idUsers, long idPost, int status, String managerId);
	public void updateAllUser(long idPost, int status, String managerId);

	public List<UserDetail> queryListUserIsView(long idPost,String userId);
}
