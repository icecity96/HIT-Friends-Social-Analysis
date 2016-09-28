package com.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dao.UserDao;
import com.po.User;
import com.service.UserServer;
@Service
public class UserServerImpl implements UserServer {
	@Resource
	private UserDao userDao;
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("nickname", user.getNickname());
		param.put("email", user.getEmail());
		
		User rsObj = userDao.findByNicknameOrEmail(param);
		
		if (rsObj != null) {
			String passwordMD5 = rsObj.getPassword();
			if (!StringUtils.isEmpty(passwordMD5) && !StringUtils.isEmpty(user.getPassword())) {
				//密码比较
				if (!PassWord.md5encode(user.getPassword()).equals(passwordMD5)) {
					return null;
				}
			}
		}
		return rsObj;
	}

	@Override
	public int register(User user) {
		Date now = new Date();
		user.setPassword(PassWord.md5encode(user.getPassword()));
		user.setHeadImg(PassWord.md5encode(user.getEmail()));
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userDao.creatUser(user);
		return user.getId();
	}

	@Override
	public boolean userIsExist(User user) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nickname", user.getNickname());
		param.put("email", user.getEmail());
		return userDao.findByNicknameOrEmail(param) != null;
	}

}
