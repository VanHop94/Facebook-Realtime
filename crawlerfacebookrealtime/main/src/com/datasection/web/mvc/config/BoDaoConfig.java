package com.datasection.web.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datasection.facebook.be.dao.ActionDao;
import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.dao.UserDao;
import com.datasection.facebook.be.dao.impl.ActionDaoImpl;
import com.datasection.facebook.be.dao.impl.CommentDaoImpl;
import com.datasection.facebook.be.dao.impl.PostDaoImpl;
import com.datasection.facebook.be.dao.impl.UserDaoImpl;
import com.datasection.facebook.thread.crawler.ThreadQueryDb;

@Configuration
public class BoDaoConfig {
	/*--------------------------DAO-----------------------------*/
	@Bean
	public UserDao createUserDao() {
		return new UserDaoImpl();
	}

	@Bean
	public CommentDao createCommentDao() {
		return new CommentDaoImpl();
	}

	@Bean
	public ThreadQueryDb threadQueryDb(){
		return new ThreadQueryDb();
	}
	
	@Bean
	public PostDao createPostDao() {
		return new PostDaoImpl();
	}

	@Bean
	public ActionDao createActionDao() {
		return new ActionDaoImpl();
	}

	/*--------------------------BO-----------------------------*/

}
