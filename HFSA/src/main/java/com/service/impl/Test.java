package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weibo4j.Account;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.model.FriendsTimelineIds;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

public class Test {
	static String accessToken = "2.00BxhlPGNDRL7D72ec2effcf2NqAQE";
	public static void main(String[] args) throws WeiboException, JSONException {
		Account account = new Account(accessToken);
		String id = account.getUid().getString("uid");
		System.out.println(id);
		Friendships friendships = new Friendships(accessToken);
		Map<String, String> map = new HashMap<String,String>();
		Timeline timeline = new Timeline(accessToken);
		StatusWapper statusWapper = timeline.getFriendsTimeline();
		List<Status> status = statusWapper.getStatuses();
		for (Status status2 : status) {
			System.out.println(status2.getUser().getName()+" : "+status2.getText());
		}
	}
}
