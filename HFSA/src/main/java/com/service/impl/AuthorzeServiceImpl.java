package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthorzeDao;
import com.service.AuthorizeService;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import weibo4j.Friendships;
import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

@Service
public class AuthorzeServiceImpl implements AuthorizeService{
	@Autowired
	private AuthorzeDao authorzeDao;

	@Override
	/**
	 * 由于新浪微博对于网站应用不提供refresh_token,故而暂时只能由用户自己设置授权时间
	 * @author ice_city
	 * @param userId 网站用户id
	 * @param token token
	 */
	public void saveWeiboAccessToken(int userId, AccessToken token) {
		authorzeDao.saveWeiboAccessToken(userId, token);
	}

}
