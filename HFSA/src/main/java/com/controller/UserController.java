package com.controller;



import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.po.Friends;
import com.po.User;
import com.service.TianyaService;
import com.service.UserServer;
import com.service.WeiboService;

@Controller
public class UserController {
	@Autowired
	private UserServer userServer;
	@Autowired
	private TianyaService tianyaService;
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	//TODO:gaoxy
	@RequestMapping(value="/HomePage",method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody 
	ModelAndView login(@RequestParam("name")String id,
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
			model.addObject("msg", "账号密码错误");
			model.setViewName("Login");
			return model;
		} else {
			rsUser.setPassword("default");
		}
		session.setAttribute("userLogin", rsUser);
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
			session.setAttribute("userLogin", user);
			modelAndView.setViewName("HomePage");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/tianjia",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody 
	void addFriends(@RequestParam("id")int id,
						@RequestParam("name")String name,
						@RequestParam("weibourl")String weibourl,
						@RequestParam("tianyaurl")String tianyaurl) throws FileNotFoundException, ClassNotFoundException, IOException {
		Friends friend = new Friends(id, name, weibourl, tianyaurl);
		int code = userServer.addFriend(friend);
		if (code==0) {
			request.setAttribute("msg", "该好友已存在");
		} else {
			request.setAttribute("msg", "成功添加好友");
			if (friend.getFriendtianya() != null && !friend.getFriendtianya().isEmpty()) {
				tianyaService.oneurlSpider(friend.getFriendtianya());
			}
			if (friend.getFriendweibo() != null && !friend.getFriendweibo().isEmpty()) {
				weiboService.oneurlSpider(friend.getFriendweibo());
			}
		}
	}
	
	@RequestMapping(value="/delFriends",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody 
	ModelAndView delFriends(@RequestParam("id")int id,@RequestParam("name")String name) {
		userServer.delFriend(id, name);
		ModelAndView model = new ModelAndView();
		request.setAttribute("friendList", userServer.getFriendList(id));
		model.setViewName("FriendsList");
		return model;
	}
	
	@Scheduled(cron="0 08 */1 * * *")
	public void SpiderForce() throws FileNotFoundException, ClassNotFoundException, IOException {
		weiboService.weiboSpider();
		tianyaService.TianyaSpider();
	}
	
}
