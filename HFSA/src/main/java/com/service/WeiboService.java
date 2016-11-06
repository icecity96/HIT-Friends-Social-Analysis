package com.service;

import java.util.List;

import weibo4j.model.Status;
import weibo4j.model.User;

public interface WeiboService {
	public List<User> getFriendsList(int userId);
	public List<Status> getFriendsStatus(int userId);
	public void saveFriendsStatus(int userId);
	public void weiboSpider();
	public void oneurlSpider(String url);
}
