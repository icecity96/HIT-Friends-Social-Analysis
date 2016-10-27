package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/*
 * Created by gaoxy on 2016/10/23 to control the page
 */
@Controller
@RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.POST})
public class PageController {
	@RequestMapping(value="/Authorize")
	public @ResponseBody 
	ModelAndView Identification(){
		ModelAndView model = new ModelAndView();
		model.setViewName("Authorize");
		return model;
	}
	
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
		model.setViewName("Login_v2");
		return model;
	}
	
	@RequestMapping(value="/SuccessAuthorize")
	public @ResponseBody
	ModelAndView SuccessConnectWEIBO(@RequestParam String code){
		ModelAndView model = new ModelAndView();
		model.setViewName("SuccessAuthorize");
		System.out.println(code);
		return model;
	}
	
	@RequestMapping(value="/AllMessage")
	public @ResponseBody 
	ModelAndView ShowAllMessage(){
		ModelAndView model = new ModelAndView();
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
	
	@RequestMapping(value="/test")
	public @ResponseBody 
	ModelAndView test(){
		ModelAndView model = new ModelAndView();
		model.setViewName("test");
		return model;
	}
}
