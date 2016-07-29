package com.datasection.facebook.be.dao;

import com.datasection.facebook.be.entities.ActionInfo;
import com.datasection.facebook.be.entities.PostDetail;

public interface ActionDao {

	public ActionInfo queryActionOfPost(PostDetail post,String userId);
	public boolean updateActionOfPost(ActionInfo actionInfo);
	
}
