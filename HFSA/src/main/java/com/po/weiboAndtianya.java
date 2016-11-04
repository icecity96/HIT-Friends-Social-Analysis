package com.po;

public class weiboAndtianya {
	private long belongto;//此属性是指此好友属于哪个用户，与用户id对应。
	private String url;
	private String time;
	private String context;
	private String type;//用以区分微博和天涯
	
	public weiboAndtianya() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor
	 * @param url
	 */
	public weiboAndtianya(long belongto,String url,String time,String context,String type) {
		this.belongto=belongto;
		this.url = url;
		this.time = time;
		this.context = context;
		this.type = type;
	}
	public long getBelongto() {
		return belongto;
	}
	public void setBelongto(long belongto) {
		this.belongto = belongto;
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
	
}
