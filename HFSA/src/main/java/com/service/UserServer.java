package com.service;
import com.po.*;
import java.util.List;
import com.dao.*;
public interface UserServer {
	public User login(User user);
	public boolean userIsExist(User user);
	public int register(User user);
	public void addWT(List<weiboAndtianya> list);
}
