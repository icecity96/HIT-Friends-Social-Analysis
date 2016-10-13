package com.service;

import com.po.User;

public interface UserServer {
	public User login(User user);
	public boolean userIsExist(User user);
	public int register(User user);
}
