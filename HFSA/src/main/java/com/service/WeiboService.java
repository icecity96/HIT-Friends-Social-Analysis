package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import weibo4j.model.Status;
import weibo4j.model.User;

public interface WeiboService {
	public List<User> getFriendsList(int userId);
	public List<Status> getFriendsStatus(int userId);
	public void saveFriendsStatus(int userId);
	public void weiboSpider() throws FileNotFoundException, ClassNotFoundException, IOException;
	public void oneurlSpider(String url) throws FileNotFoundException, ClassNotFoundException, IOException;
}
