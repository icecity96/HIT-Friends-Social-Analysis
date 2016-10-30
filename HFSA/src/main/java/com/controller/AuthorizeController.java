package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.User;
import com.service.AuthorizeService;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

@Controller
public class AuthorizeController {
	@Autowired
	private static AuthorizeService authorizeService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	//TODO:gaoxy
	/**
	 * 微博授权
	 * @return 微博认证页面
	 * @throws WeiboException
	 */
	@RequestMapping("/connectWeibo")
	public String 
	authorizeWeibo() throws WeiboException {
		Oauth oauth = new Oauth();
		String url = oauth.authorize("code");
		return "redirect:"+url;
	}
	
	//TODO:gaoxy
	/**
	 * 认证结束后获取code，传入该函数，以换取access_token
	 * 存入数据库
	 * @param code
	 * @param userId
	 * @throws WeiboException
	 */
	public static void 
		saveAccessTokenByCode(String code,
							String userId) throws WeiboException {
		Oauth oauth = new Oauth();
		System.out.println(code);
		System.out.println(userId);
		AccessToken accessToken = oauth.getAccessTokenByCode(code);
		System.out.println(accessToken.getAccessToken());
		authorizeService.saveWeiboAccessToken(Integer.parseInt(userId), accessToken);
	}
	
}
