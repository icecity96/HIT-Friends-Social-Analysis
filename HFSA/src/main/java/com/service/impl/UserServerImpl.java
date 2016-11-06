package com.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dao.UserDao;
import com.dao.WTDao;
import com.dao.FriendsDao;
import com.po.User;
import com.po.weiboAndtianya;
import com.service.UserServer;

@Service
public class UserServerImpl implements UserServer {
	@Autowired
	private UserDao userDao;
	@Autowired
	public  WTDao wtdao;
	@Autowired
	public  FriendsDao fr;
	
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
	/**
	 * 注册成功返回user.id，密码不合法返回－1，用户已存在返回－2
	 */
	public int register(User user) {
		//密码是否合法，用户是否存在
		if (!PassWord.isLegitimate(user.getPassword())) {
			return -1;
		}
		if (userIsExist(user)) {
			return -2;
		}
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

	@Override
	public List<weiboAndtianya> latestMov(long id)
	{
		fr.findfriend(id);
		List<weiboAndtianya> ll=new ArrayList<weiboAndtianya>();
		ll=fr.latestMovement();
		fr.clean();
		return ll;
	}
}
