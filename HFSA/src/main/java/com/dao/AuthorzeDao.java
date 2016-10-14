package com.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;


public interface AuthorzeDao {
	//TODO:lx
	@Insert("insert into AccessToken(accessToken,expireIn,refreshToken) values(#{accessToken.accessToken},#{accessToken.expireIn},#{accessToken.refreshToken}) where uid=userId")
	public void saveWeiboAccessToken(@Param("userId")int userId,
								@Param("accessToken")AccessToken accessToken);
	//TODO:lx
	/**
	 * 返回数据库中已有的最新微博的发布时间
	 * @param userId
	 * @return
	 */
	@Select("select latestUpdateTime from status where userId=#{userId} ")
	public Date getNewestWeibotime(@Param("userId")int userId);
	
	/**
	 * 添加一条微博信息到数据库中
	 * @param userId 用户ID
	 * @param status 微博信息类
	 */
	@Insert("insert into userId values(#{status.id},#{status.text},#{status.inReplyToUserId},#{status.repostsCount},#{status.commentsCount},#{status.favorited})")
	public void addWeibo(@Param("userId")int userId,@Param("status")Status status);
	
	/**
	 * 根据用户Id提取出其微博的access_token
	 * @param userId
	 * @return
	 */
	@Select("select * from AccessToken where uid=#{userId}")
	public AccessToken getWeiboAccessToken(@Param("userId")int userId);
}
