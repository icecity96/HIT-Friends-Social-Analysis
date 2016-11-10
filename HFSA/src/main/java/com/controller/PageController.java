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

import com.service.UserServer;



/*
 * Created by gaoxy on 2016/10/23 to control the page
 */
@Controller
@RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.POST})
public class PageController {
	@Autowired
	private UserServer userServer;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/ProFile")
	public @ResponseBody 
	ModelAndView ProFile(){
		ModelAndView model = new ModelAndView();
		model.setViewName("ProFile");
		return model;
	}
	
	@RequestMapping(value="/Login")
	public @ResponseBody 
	ModelAndView BacktoLogin(){
		ModelAndView model = new ModelAndView();
		model.setViewName("Login");
		return model;
	}
	
	@RequestMapping(value="/AllMessage")
	public @ResponseBody 
	ModelAndView ShowAllMessage(@RequestParam("id")int id){
		ModelAndView model = new ModelAndView();
		System.out.println('1');
		System.out.println(id);
		request.setAttribute("friendsStatus", userServer.latestMov(id));
		model.setViewName("AllMessage");
		return model;
	}
	
	@RequestMapping(value="/FriendsList")
	public @ResponseBody 
	ModelAndView ShowFriednsList(){
		ModelAndView model = new ModelAndView();
		model.setViewName("FriendsList");
		return model;
	}
	
	@RequestMapping(value="/AddFriend")
	public @ResponseBody 
	ModelAndView AddFriend(){
		ModelAndView model = new ModelAndView();
		model.setViewName("AddFriend");
		return model;
	}
	
	@RequestMapping(value="/SpecificFriend")
	public @ResponseBody 
	ModelAndView test(){
		ModelAndView model = new ModelAndView();
		model.setViewName("SpecificFriend");
		return model;
	}
}
