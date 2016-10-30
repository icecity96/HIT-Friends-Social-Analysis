package com.service;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import weibo4j.http.AccessToken;

public interface AuthorizeService {
	public void saveWeiboAccessToken(int userId,AccessToken token);
}
