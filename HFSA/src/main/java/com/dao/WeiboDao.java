package com.dao;

import org.apache.ibatis.annotations.Param;

import weibo4j.model.Status;

public interface WeiboDao {
	/**
	 * 
	 * @param friendId 用户好友的微博id
	 * @return 用户好友在数据库中最新微博的id
	 */
	public int getLastWeiboId(@Param("friendId")int friendId);
	/**
	 * 
	 * @param friendId 用户好友id
	 * @param status 要存入的微博
	 */
	public void saveFriendWeibo(@Param("friendId")int friendId,@Param("status")Status status);
}
