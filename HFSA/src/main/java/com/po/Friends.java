package com.po;

public class Friends {
	private long id;
	private String friendname;
	private String friendweibo;
	private String friendtianya;
	
	public Friends() {
		// TODO Auto-generated constructor stub
	}
	public Friends(int id,String friendname,String friendweibo,String friendtianya) {
		this.id = id;
		this.friendname = friendname;
		this.friendweibo = friendweibo;
		this.friendtianya = friendtianya;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public String getFriendweibo() {
		return friendweibo;
	}
	public void setFriendweibo(String friendweibo) {
		this.friendweibo = friendweibo;
	}
	public String getFriendtianya() {
		return friendtianya;
	}
	public void setFriendtianya(String friendtianya) {
		this.friendtianya = friendtianya;
	}
	
}
