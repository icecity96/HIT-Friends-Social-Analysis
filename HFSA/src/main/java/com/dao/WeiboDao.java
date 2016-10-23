package com.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import weibo4j.model.Status;

public interface WeiboDao {
	/**
	 * 
	 * @param friendId 用户好友的微博id
	 * @return 用户好友在数据库中最新微博的id
	 */
	@Select("select sId from status where id=#{friendId} order by createAt")
	public int getLastWeiboId(@Param("friendId")int friendId);
	/**
	 * 
	 * @param friendId 用户好友id
	 * @param status 要存入的微博
	 */
	@Insert("insert into status(userId,id,createdAt,text,inReplyToUserId,repostsCount,commentsCount) values(#{friendId},#{status.id},#{status.createdAt},#{status.text},#{status.inReplyToUserId},#{status.repostsCount},#{status.commentsCount})")
	public void saveFriendWeibo(@Param("friendId")int friendId,@Param("status")Status status);
}