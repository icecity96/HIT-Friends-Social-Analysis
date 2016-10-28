package com.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.po.User;
import com.service.UserServer;

@Controller
public class UserController {
	@Autowired
	private UserServer userServer;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login_v2");
		return modelAndView;
	}
	//TODO:gaoxy
	@RequestMapping(value="/login",method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody 
	ModelAndView login(@RequestParam("id")String id,
						@RequestParam("password")String password){
		//判断邮箱登录还是用户名登录
		String email = null;
		String nickname = null;
		if (id.indexOf("@")>0) {
			email = id;
		} else {
			nickname = id;
		}
		
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		user.setPassword(password);
		User rsUser = userServer.login(user);
		ModelAndView model = new ModelAndView();
		if (rsUser==null) {
			//TODO:gaoxy
			model.setViewName("failue");
			return model;
		} else {
			rsUser.setPassword("default");
		}
		model.setViewName("HomePage");
		return model;
	}
	
	//TODO:gaoxy
	@RequestMapping(value="/register",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody 
	ModelAndView userRegister(@RequestParam("nickname")String nickname,
						@RequestParam("email")String email,
						@RequestParam("password")String password,
						@RequestParam("confirmPassword")String confirmPassword){
		ModelAndView modelAndView = new ModelAndView();
		//检查两次输入密码是否相同
		if(!password.equals(confirmPassword)) {
			//TODO:gaoxy
			modelAndView.addObject("msg", "两次密码不同");
			modelAndView.setViewName("false");
			return modelAndView;
		}
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setNickname(nickname);
		//用户注册
		int userId = userServer.register(user);
		//TODO:gaoxy
		switch (userId) {
		case -1:
			modelAndView.addObject("msg", "密码格式错误");
			modelAndView.setViewName("false");
		case -2:
			modelAndView.addObject("msg", "用户名或邮箱已被占用");
			modelAndView.setViewName("false");
		default:
			modelAndView.addObject("userIdRegister", userId);
			modelAndView.setViewName("HomePage");
		}
		return modelAndView;
	}

}
