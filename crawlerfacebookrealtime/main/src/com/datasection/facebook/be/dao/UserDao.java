package com.datasection.facebook.be.dao;

import java.util.List;

import com.datasection.facebook.be.entities.UserDetail;

public interface UserDao {
	public boolean addListUser(List<UserDetail> list);

	public boolean addUser(UserDetail user);

	public UserDetail queryUser(String id);

	public List<UserDetail> queryListUser(String idPost,long created_time, String userId);
	
	public boolean updateUsers(String [] idUsers, String idPost, int status, String managerId);
	public void updateAllUser(String idPost, int status, String managerId);

	public List<UserDetail> queryListUserIsView(String idPost,String userId);
}
