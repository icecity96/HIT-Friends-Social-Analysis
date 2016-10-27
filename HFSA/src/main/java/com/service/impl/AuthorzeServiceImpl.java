package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthorzeDao;
import com.service.AuthorizeService;
import weibo4j.http.AccessToken;

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
