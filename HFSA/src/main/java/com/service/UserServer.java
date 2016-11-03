package com.service;

import java.util.List;

import com.po.Friends;
import com.po.User;
import com.po.weiboAndtianya;

public interface UserServer {
	public User login(User user);
	public boolean userIsExist(User user);
	public int register(User user);
	public int addFriend(Friends friend);
	public List<weiboAndtianya> lastesMovements(int id);
}
