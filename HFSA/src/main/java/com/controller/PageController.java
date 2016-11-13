package com.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.po.Result;
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
		List<Result> l = userServer.latestMov(id);
		request.setAttribute("friendsStatus", userServer.latestMov(id));
		model.setViewName("AllMessage");
		return model;
	}
	
	@RequestMapping(value="/FriendsList")
	public @ResponseBody 
	ModelAndView ShowFriednsList(@RequestParam("id")int id){
		ModelAndView model = new ModelAndView();
		System.out.println('1');
		System.out.println(id);
		request.setAttribute("friendList", userServer.getFriendList(id));
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
	ModelAndView SpecificFriend(@RequestParam("id")int id,@RequestParam("friendName")String friendName) throws ParseException{
		ModelAndView model = new ModelAndView();
		System.out.println('1');
		System.out.println(id);
		System.out.println(friendName);
		List<Result> l = userServer.getOneFrinedMov(id, friendName);
		System.out.println(l.size());
		request.setAttribute("specificFriend", userServer.getOneFrinedMov(id, friendName));
		List<Result> friendMov = userServer.getOneFrinedMov(id, friendName);
		int[] topic = userServer.getFriendTopic(friendMov);
		int maxNum = topic[0];
		for(int i=1;i<topic.length;i++){
			if(topic[i] > maxNum){
				maxNum = topic[i];
			}
		}
		request.setAttribute("maxNum", maxNum);
		request.setAttribute("frendtopic", userServer.getFriendTopic(friendMov));
		request.setAttribute("weekMov", userServer.getWeekMov(friendMov));
		model.setViewName("SpecificFriend");
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
