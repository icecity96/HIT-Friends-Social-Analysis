package com.po;

public class weiboAndtianya {
	//此属性是指此好友属于哪个用户，与用户id对应。
	private String url;
	private String time;
	private String context;
	private String type;//用以区分微博和天涯
	private int topic;//兴趣分类
	
	public weiboAndtianya() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor
	 * @param url
	 */
	public weiboAndtianya(String url,String time,String context,String type,int topic) {
		
		this.url = url;
		this.time = time;
		this.context = context;
		this.type = type;
		this.topic=topic;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTopic()
	{
		return topic;
	}
	public void setTopic(int topic)
	{
		this.topic=topic;
	}
}
