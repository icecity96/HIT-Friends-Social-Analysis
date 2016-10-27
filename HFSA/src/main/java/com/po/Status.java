package com.po;

import java.util.Date;
public class Status {
	private long userId;//好友在网站的id
	private long id;//此条微博的微博id
	private Date createdAt;//创建时间
	private String text;//微博文字内容
	private long inReplyToUserId;//回复人id
	private int repostsCount;//转发数
	private int commentsCount;//评论数
	public long getuserId()
	{
		return userId;
	}
	public long  getid()
	{
		return id;
	}
	public Date getcreatedAt()
	{
		return createdAt;
	}
	public String gettext()
	{
		return text;
	}
	public long getinReplyToUserId()
	{
		return inReplyToUserId;
	}
	public int getrepostsCount()
	{
		return repostsCount;
	}
	public int getcommentsCount()
	{
		return commentsCount;
	}
	
	public void setuserId()
	{
		this.userId=userId;
	}
	public void  setid()
	{
		this.id=id;
	}
	public void setcreatedAt()
	{
		this.createdAt=createdAt;
	}
	public void settext()
	{
		this.text=text;
	}
	public void setinReplyToUserId()
	{
		this.inReplyToUserId=inReplyToUserId;
	}
	public void setrepostsCount()
	{
		this.repostsCount=repostsCount;
	}
	public void setcommentsCount()
	{
		this.commentsCount=commentsCount;
	}
	
}
