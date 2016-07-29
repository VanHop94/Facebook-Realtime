package com.datasection.facebook.be.dao;

import com.datasection.facebook.be.entities.HumanDetail;

public interface HumanDao {

	public HumanDetail checkLogin(String username,String password);
	
}
