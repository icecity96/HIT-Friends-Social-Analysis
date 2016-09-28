package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.User;
import com.service.UserServer;

@Controller
public class UserController {
	@Resource
	private UserServer userServer;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取表单提交的数据
		String id = request.getParameter("id");
		String passwd = request.getParameter("password");
		//返回结果
		Map<String, Object> rsMap = new HashMap<String,Object>();
		//判断用户名和密码是否为空,－1表示用户名为空，－2表示密码为空
		if (id.isEmpty()) {
			rsMap.put("message", "用户名不能为空");
			return rsMap;
		}
		if (passwd.isEmpty()) {
			rsMap.put("message","密码不能为空");
			rsMap.put("code", -2);
			return rsMap;
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
			rsMap.put("message", "用户名或密码错误");
			rsMap.put("code", 0);
		} else {
			rsUser.setPassword("default");
			rsMap.put("message", "成功登录");
			rsMap.put("code", 1);
			rsMap.put("user", rsUser);
		}
		return rsMap;
	}
}
