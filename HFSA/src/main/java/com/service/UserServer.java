package com.service;

import org.springframework.stereotype.Repository;

import com.po.User;

public interface UserServer {
	public User login(User user);
	public boolean userIsExist(User user);
	public int register(User user);
}
