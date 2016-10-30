package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dao.AuthorzeDao;
import com.dao.UserDao;
import com.dao.WeiboDao;
import com.service.WeiboService;
import weibo4j.Account;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
@Service
public class WeiboServiceImpl implements WeiboService{
	@Autowired
	private AuthorzeDao authorzeDao;
	@Autowired
	private WeiboDao weiboDao;
	@Autowired
	private UserDao userDao;
	/**
	 * @author ice_city
	 * @param userId 网站用户id
	 * @return 用户好友列表,授权用户可以全部返回，非授权用户只能返回30%
	 */
	@Override
	public List<User> getFriendsList(int userId) {
		AccessToken token = authorzeDao.getWeiboAccessToken(userId);
		String accessToken = token.getAccessToken();
		Friendships friendships = new Friendships(accessToken);
		Account account = new Account(accessToken);
		UserWapper userWapper = null;
		try {
			userWapper = friendships.getFriendsByID(account.getUid().getString("uid"));
		} catch (WeiboException | JSONException e) {
			e.printStackTrace();
		}
		return userWapper.getUsers();
	}

	/**
	 * @author ice_city
	 * @param userId 网站用户id
	 * @return 用户好友最新微博，受限于第三方，只能获取前30%
	 */
	@SuppressWarnings("serial")
	@Override
	public List<Status> getFriendsStatus(int userId) {
		AccessToken token = authorzeDao.getWeiboAccessToken(userId);
		String accessToken = token.getAccessToken();
		Timeline timeline = new Timeline(accessToken);
		int since_id = authorzeDao.getNewestWeibotime(userId);
		Map<String, String> map = new HashMap<String,String>(){{put("since_id", String.valueOf(since_id));}};
		StatusWapper statusWapper = null;
		try {
			statusWapper = timeline.getFriendsTimeline(map);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return statusWapper.getStatuses();
	}

	/**
	 * @author ice_city
	 * @param userId
	 * 这个函数用于将新获得的好友微博存入数据库
	 */
	@Override
	public void saveFriendsStatus(int userId) {
		List<Status> status = getFriendsStatus(userId);
		for (Status friendStatu : status) {
			if (weiboDao.existUserId(Integer.parseInt(friendStatu.getUser().getId())) != 0 &&
					friendStatu.getIdstr() > weiboDao.getLastWeiboId(Integer.parseInt(friendStatu.getUser().getId()))) {
				weiboDao.saveFriendWeibo(Integer.parseInt(friendStatu.getUser().getId()), friendStatu);
			}
		}
	}
	
	/**
	 * @author ice_city
	 * 后台定时任务，每隔5分钟，执行一次，用于刷取好友动态
	 */
	@Scheduled(fixedDelay=300000)
	public void updateFriendWeiboStatus() {
		List<Integer> userIds = userDao.getAllUserId();
		for (Integer integer : userIds) {
			saveFriendsStatus(integer.intValue());
		}
	}
	
}
