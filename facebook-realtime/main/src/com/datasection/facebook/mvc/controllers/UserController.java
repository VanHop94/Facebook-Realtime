package com.datasection.facebook.mvc.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.dao.UserDao;
import com.datasection.facebook.be.entities.PostDetail;
import com.datasection.facebook.be.entities.UserDetail;

import net.arnx.jsonic.JSON;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PostDao postDao;
	
	@RequestMapping("post/{idPost}/user/manager")
	public @ResponseBody String viewAllUser(@PathVariable("idPost") long idPost, HttpServletRequest request, HttpSession session){
		
		List<UserDetail> listUsers = null;
		
		long created_time = Long.parseLong(request.getParameter("created_time"));
		try {
			int request_times = Integer.parseInt(request.getParameter("request_times"));
			PostDetail postDetail = postDao.queryPost(idPost, (String) session.getAttribute("user_id"));
			if(request_times == 1)
				if(postDetail.getUpdate_util() < (new Date()).getTime())
					return "";
			
			listUsers = userDao.queryListUser(idPost, created_time, (String) session.getAttribute("user_id"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON.encode(listUsers);
	}
	
	@RequestMapping("post/{idPost}/user/isview")
	public @ResponseBody List<UserDetail> viewUserIsView(@PathVariable("idPost") long idPost, HttpSession session){
		List<UserDetail> listUsers = userDao.queryListUserIsView(idPost, (String) session.getAttribute("user_id"));
		return listUsers;
	}
	
	@RequestMapping(value = "post/{idPost}/user/update", method = RequestMethod.POST)
	public @ResponseBody boolean updateUser(@RequestParam("user_ids") String [] user_ids,@RequestParam("post_id") long post_id, @RequestParam("status") int status, HttpSession session){
		
		if(status == 0)
			commentDao.updateCommentsOfUser(user_ids, post_id, status, (String) session.getAttribute("user_id"));
		
		return userDao.updateUsers(user_ids, post_id, status, (String) session.getAttribute("user_id"));
		
	}
	@RequestMapping(value = "post/{idPost}/user/updateAll", method = RequestMethod.POST)
	public @ResponseBody void updateUserAll(@RequestParam("post_id") long post_id, @RequestParam("status") int status, HttpSession session){
		
		if(status == 0)
			commentDao.updateAllCommentsOnPost(post_id, status);
		
		userDao.updateAllUser(post_id, status, (String) session.getAttribute("user_id"));
		
	}
	
}
