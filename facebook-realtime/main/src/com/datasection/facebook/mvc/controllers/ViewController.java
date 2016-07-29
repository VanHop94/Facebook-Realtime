package com.datasection.facebook.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.entities.PostDetail;
import com.datasection.web.mvc.config.ArgsConstant;

@Controller
@EnableWebMvc
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	private ArgsConstant argsConstant;
	@Autowired
	private PostDao postDao;
	
	@RequestMapping(value = "post/management",method = RequestMethod.GET)
	public ModelAndView post_management(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView model = new ModelAndView("post_management");
		model.addObject("name", session.getAttribute("name"));
		return model;
	}
	
	@RequestMapping(value = "/post/list_post")
	public ModelAndView list_post(HttpSession session){
		ModelAndView model = new ModelAndView("list_post");
		model.addObject("name", session.getAttribute("name"));
		return model;
	}
	
	@RequestMapping("/post/{idPost}/graph_realtime")
	public ModelAndView view_graph_realtime(@PathVariable("idPost") String idPost, HttpServletRequest request, HttpSession session){
		ModelAndView model = new ModelAndView("graph_realtime");
		
		PostDetail postDetail = postDao.queryPost(idPost, (String) session.getAttribute("user_id"));
		
		request.setAttribute("request_times", 0);
		model.addObject("id", postDetail.getId());
		model.addObject("name", session.getAttribute("name"));
		model.addObject("timeCallServer", argsConstant.getTimeCallServer());
		return model;
	}
	
	@RequestMapping(value = "/post/{id}/manager" , method = RequestMethod.GET)
	public ModelAndView manageCommentsOnPost(@PathVariable("id") String id, HttpSession session, HttpServletRequest request){
		ModelAndView model = new ModelAndView("block");
		
		PostDetail postDetail = postDao.queryPost(id, (String) session.getAttribute("user_id"));
		
		String keyWords = "";
		
		if(request.getParameter("keyword") != null)
			keyWords = request.getParameter("keyword");
		
		model.addObject("keyword",keyWords.toLowerCase());
		model.addObject("idPost", postDetail.getId());
		model.addObject("name", session.getAttribute("name"));
		model.addObject("timeToRepeat", argsConstant.getTimeToRepeat());
		return model;
	}
	
}
