package com.datasection.facebook.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:view/post/management");
		return model;
	}
}
