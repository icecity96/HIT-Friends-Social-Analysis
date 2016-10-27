package com.dao;

import org.apache.ibatis.annotations.Param;

import weibo4j.model.Status;

public interface WeiboDao {
	/**
	 * 
	 * @param friendId 用户好友的微博id
	 * @return 用户好友在数据库中最新微博的id
	 */
	public int getLastWeiboId(@Param("friendId")long friendId);
	/**
	 * 
	 * @param friendId 用户好友id
	 * @param status 要存入的微博
	 */
	public void saveFriendWeibo(@Param("friendId")long friendId,@Param("status")Status status);
	
	/**
	 * 
	 * @param friendId
	 * @return 如果数据库中有这个id则返回这个id，否则请返回其它不合理值
	 */
	public int existUserId(@Param("friendId")long friendId);
}
