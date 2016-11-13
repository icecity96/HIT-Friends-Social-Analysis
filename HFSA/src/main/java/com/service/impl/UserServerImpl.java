package com.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dao.FriendsDao;
import com.dao.UserDao;
import com.po.Friends;
import com.po.Result;
import com.po.User;
import com.po.weiboAndtianya;
import com.service.UserServer;

@Service
public class UserServerImpl implements UserServer {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendsDao friendsDao;
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
		int id = userDao.getMaxid();
		user.setId(id);
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
	public int addFriend(Friends friend) {
		//如果这个好友已经添加过
		if (friendsDao.allFriends((int)friend.getId()) != null &&
			friendsDao.allFriends((int)friend.getId()).contains(friend.getFriendname())) {
			return 0;
		}
		try {
			friendsDao.insertFriend(friend);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<Result> latestMov(long id)
	{
		friendsDao.findfriend(id);
		List<weiboAndtianya> ll=new ArrayList<weiboAndtianya>();
		List<Result> results = new ArrayList<>();
		ll=friendsDao.latestMovement();
		friendsDao.clean();
		for (weiboAndtianya weiboAndtianya : ll) {
			results.add(new Result(weiboAndtianya, friendsDao.FindFriendByUrl(weiboAndtianya)));
		}
		Collections.sort(results);
		return results;
	}

	@Override
	public List<Result> getOneFrinedMov(long id, String friendName) {
		friendsDao.findOnefriend(id, friendName);
		List<weiboAndtianya> oneFriendMov = friendsDao.latestMovement();
		friendsDao.clean();
		List<Result> results = new ArrayList<>();
		for (weiboAndtianya weiboAndtianya : oneFriendMov) {
			results.add(new Result(weiboAndtianya, friendName));
		}
		Collections.sort(results);
		return results;
	}
	@Override
	public void delFriend(int id, String friendName) {
		friendsDao.deletefriend(friendName, id);
	}
	
	@Override
	public void updateFriend(Friends friend) {
		
	}

	@Override
	public List<Friends> getFriendList(long id) {
		return friendsDao.allFriends(id);
	}

	@Override
	public int[] getFriendTopic(List<Result> friendMov) {
		int[] result = new int[10];
		for (int i = 0, j = 0; i < friendMov.size() && j <= 100; i++,j++) {
			result[friendMov.get(i).getStatu().getTopic()]++;
		}
		return result;
	}

	@Override
	public int[] getWeekMov(List<Result> friendMov) throws ParseException {
		int[] result = new int[7];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		for (Result mov : friendMov) {
			Date newDate = simpleDateFormat.parse(mov.getStatu().getTime().substring(0, 10));
			System.out.println(date.getTime() + " "+ newDate.getTime());
			long day = (date.getTime() - newDate.getTime())/(24*60*60*1000);
			System.out.println(day);
			if (day > 6) {
				break;
			}
			result[6-(int)day]++;
		}
		for (int i : result) {
			System.out.println(i);
		}
		return result;
	}
}
