package com.service;

import weibo4j.http.AccessToken;

public interface AuthorizeService {
	public void saveWeiboAccessToken(int userId,AccessToken token);
	public void updateWeiboDatabase(int userId);
}
