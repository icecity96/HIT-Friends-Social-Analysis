package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.service.WeiboService;

@Controller
public class FriendListController {
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@RequestMapping(value="/WeiboFriendlist",method={RequestMethod.POST,RequestMethod.GET})
	List<weibo4j.model.User> getWeiboFriendlist(@RequestParam("userId")int userId) {
		return weiboService.getFriendsList(userId);
	}
}
