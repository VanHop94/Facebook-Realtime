package com.datasection.facebook.mvc.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.datasection.facebook.be.dao.HumanDao;
import com.datasection.facebook.be.entities.HumanDetail;
import com.datasection.facebook.utils.StringUtil;

@Controller
@RequestMapping("/login")
public class LoginLogoutController {

	@Autowired
	private HumanDao humanDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView model = new ModelAndView("login");
		return model;

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> params, HttpSession session) {

		ModelAndView model = new ModelAndView();

		String username = params.get("username");
		String pass = params.get("password");

		if (!StringUtil.isUsername(username)) {
			model.setViewName("login");
			model.addObject("error", "Tên đăng nhập không hợp lệ!");
			Logger.getLogger(getClass()).error("Tên đăng nhập không hợp lệ!" + "\t" + username);
			return model;
		}

		HumanDetail user = humanDao.checkLogin(username, StringUtil.md5(pass));
		if (user == null) {
			model.setViewName("login");
			model.addObject("error", "Tên đăng nhập hoặc mật khẩu sai!");
			Logger.getLogger(getClass()).error("Tên đăng nhập hoặc mật khẩu sai!" + "\t" + username);
		} else {
			session.setAttribute("rule", user.getRule());
			session.setAttribute("name", user.getUsername());
			session.setAttribute("user_id", user.getUsername());
			model.setViewName("redirect:view/post/management");
		}
		return model;

	}

	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {

		ModelAndView model = new ModelAndView("redirect:login");
		session.invalidate();
		return model;
	}

}
