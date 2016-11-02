package com.po;

public class weiboAndtianya {
	private long belongto;//此属性是指此好友属于哪个用户，与用户id对应。
	private String url;
	private String time;
	private String context;
	private String type;//用以区分微博和天涯
	long getbelongto()
	{
		return belongto;
	}
	String geturl()
	{
		return url;
	}
	String gettime()
	{
		return time;
	}
	String getcontext()
	{
		return context;
	}
	String gettype()
	{
		return type;
	}
	
	void setbelongto()
	{
		this.belongto=belongto;
	}
	void seturl()
	{
		this.url=url;
	}
	void settime()
	{
		this.time=time;
	}
	void setcontext()
	{
		this.context=context;
	}
	void settype()
	{
		this.type=type;
	}
}
