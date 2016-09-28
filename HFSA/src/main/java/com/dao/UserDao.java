package com.dao;

import java.util.Map;

import com.po.User;

public interface UserDao {
	public User findByNicknameOrEmail(Map<String, Object> param);
	public void creatUser(User user);
}
