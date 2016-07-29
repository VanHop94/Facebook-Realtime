package com.datasection.facebook.mvc.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.entities.CommentDetail;
import com.datasection.facebook.be.entities.PostDetail;
import com.datasection.facebook.utils.Constant;

import net.arnx.jsonic.JSON;

@Controller
@EnableWebMvc
public class CommentController {

	final static Logger logger = Logger.getLogger(CommentController.class);
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PostDao postDao;
	
	@RequestMapping(value = "post/{id}/comments/manager", method = RequestMethod.GET)
	public @ResponseBody String fecthCommentsToManager(@PathVariable("id") long id, HttpServletRequest request, HttpSession session) {
		List<CommentDetail> listComments = new ArrayList<CommentDetail>();
		try {
			
			String keyWords = request.getParameter("keyword");
			
			int request_times = Integer.parseInt(request.getParameter("request_times"));
			PostDetail postDetail = postDao.queryPost(id, (String) session.getAttribute("user_id"));
			if(request_times == 1){
				if(postDetail.getUpdate_util() < (new Date()).getTime())
					return String.valueOf(Constant.POST_IS_EXPIRED);
			}
			if(keyWords.equals("") || keyWords == null)
				listComments = commentDao.queryListComments(id, (String) session.getAttribute("user_id"));
			else
				listComments = commentDao.queryListComments(id, (String) session.getAttribute("user_id"), keyWords);
		} catch(Exception e){
			logger.error("fecthCommentsToManager" , e);
			return String.valueOf(Constant.POST_IS_DELETED);
		}
		return JSON.encode(listComments);
	}	
	
	@RequestMapping(value = "post/{id}/comments/updateComments", method = RequestMethod.POST)
	public @ResponseBody boolean updateListComments(@RequestParam("listIds") String [] listIds, @RequestParam("post_id") long post_id, @RequestParam("status") int status, HttpSession session){
		return commentDao.updateComments(listIds, status, post_id, (String) session.getAttribute("user_id"));
	}
	@RequestMapping(value = "post/{id}/comments/updateCommentsAll", method = RequestMethod.POST)
	public @ResponseBody void updateAllCommentsOnPost(@RequestParam("post_id") long post_id, @RequestParam("status") int status){
		commentDao.updateAllCommentsOnPost(post_id, status);
	}
	
}
