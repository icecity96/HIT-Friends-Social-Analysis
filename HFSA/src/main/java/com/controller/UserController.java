package com.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.auth.AUTH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.po.Friends;
import com.po.User;
import com.service.AuthorizeService;
import com.service.TianyaService;
import com.service.UserServer;
import com.service.WeiboService;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

@Controller
public class UserController {
	@Autowired
	private UserServer userServer;
	@Autowired
	private TianyaService tianyaService;
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private AuthorizeService authorizeService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test");
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
			model.setViewName("Login_v2");
			return model;
		} else {
			rsUser.setPassword("default");
		}
		session.setAttribute("userLogin", rsUser);
		model.setViewName("HomePage");
		return model;
	}
	
	@RequestMapping(value="/HomePageSA",method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody 
	ModelAndView login(@RequestParam("name")String id,
						@RequestParam("password")String password,
						 @RequestParam("code")String code) throws WeiboException{
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
			model.setViewName("Login_v2");
			return model;
		} else {
			rsUser.setPassword("default");
		}
		session.setAttribute("userLogin", rsUser);
		String userId = rsUser.getId().toString();
		Oauth oauth = new Oauth();
		AccessToken token = oauth.getAccessTokenByCode(code);
		authorizeService.saveWeiboAccessToken(rsUser.getId().intValue(), token);
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
	
	@RequestMapping(value="/addFriends",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody 
	void addFriends(@RequestParam("id")int id,
						@RequestParam("name")String name,
						@RequestParam("weibourl")String weibourl,
						@RequestParam("tianyaurl")String tianyaurl) {
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
	void delFriends(@RequestParam("id")int id,@RequestParam("name")String name) {
		userServer.delFriend(id, name);
	}
	
	/**
	 * 返回用户所有的好友的部分动态
	 * @param id
	 */
	@RequestMapping(value="/lastesMovements",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody 
	void lastesMovements(@RequestParam("id")int id) {
		request.setAttribute("friendsStatus", userServer.latestMov(id));
	}
	
	/**
	 * 获取特定好友的所有动态
	 */
	@RequestMapping(value="/onefriendmov",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	void getOneFriendMov(@RequestParam("id")int id,@RequestParam("friendName")String friendName) {
		request.setAttribute(friendName+"mov", userServer.getOneFrinedMov(id, friendName));
	}
	
	@RequestMapping(value="/friendList",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	void getAllFriend(@RequestParam("id")int id) {
		request.setAttribute("friendList", userServer.getFriendList(id));
	}
	
	//@Scheduled(cron="0 3 */1 * * *")
	public void SpiderForce() {
		weiboService.weiboSpider();
		tianyaService.TianyaSpider();
	}
	
}
