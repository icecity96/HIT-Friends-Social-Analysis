package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.User;
import com.service.UserServer;
import com.util.StringUtil;

@Controller
public class UserController {
	@Resource
	private UserServer userServer;

	public String login(HttpServletRequest request, HttpServletResponse response){
		//获取表单提交的数据
		String id = request.getParameter("id");
		String passwd = request.getParameter("password");
		
		HttpSession session = request.getSession();
		//判断用户名和密码是否为空
		if (id.isEmpty()) {
			request.setAttribute("msg", "用户名不能为空");
			return "failure";
		}
		if (passwd.isEmpty()) {
			request.setAttribute("msg", "密码不能为空");
			return "failure";
		}
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
		user.setPassword(passwd);
		User rsUser = userServer.login(user);
		//code 0 表示用户名或密码错误 1 表示成功登录
		if (rsUser==null) {
			request.setAttribute("msg", "用户名或密码错误");
			return "failure";
			
		} else {
			rsUser.setPassword("default");
			request.setAttribute("msg", "成功登录");
			session.setAttribute("userLogin", rsUser);
		}
		return "success";
	}
	
	public String userRegister(HttpServletRequest request, HttpServletResponse response){
		//获取表单数据
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		//检查邮箱格式是否合法
		if (StringUtil.isEmail(email)==false) {
			request.setAttribute("msg", "邮箱格式不合法");
			return "failure";
		}
		//检查两次输入密码是否相同
		if(!password.equals(confirmPassword)) {
			request.setAttribute("msg", "两次密码输入不一致");
			return "failure";
		}
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setNickname(nickname);
		//用户注册
		int userId = userServer.register(user);
		switch (userId) {
		case -1:
			request.setAttribute("msg", "密码格式错误");
			return "failure";
		case -2:
			request.setAttribute("msg", "用户名或邮箱已被占用");
			return "failure";
		default:
			request.setAttribute("userId", userId);
			return "success";
		}
	}
}
