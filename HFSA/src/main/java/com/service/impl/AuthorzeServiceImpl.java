package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthorzeDao;
import com.service.AuthorizeService;

import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

@Service
public class AuthorzeServiceImpl implements AuthorizeService{
	@Autowired
	private AuthorzeDao authorzeDao;

	@Override
	public void saveWeiboAccessToken(int userId, AccessToken token) {
		authorzeDao.saveWeiboAccessToken(userId, token);
	}

	@Override
	public void updateWeiboDatabase(int userId) {
		AccessToken accessToken = authorzeDao.getWeiboAccessToken(userId);
		//获取最新20条动态
		Timeline timeline = new Timeline(accessToken.getAccessToken());
		try {
			StatusWapper statusWapper = timeline.getUserTimeline();
			List<Status> status = statusWapper.getStatuses();
			Date newest = authorzeDao.getNewestWeibotime(userId);	//数据库中已有的最新微博发表时间
			for (Status status2 : status) {
				//若该微博比已有最新微博新，则加入数据库
				if (status2.getCreatedAt().after(newest)) {
					authorzeDao.addWeibo(userId, status2);
					continue;
				}
				break;
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
