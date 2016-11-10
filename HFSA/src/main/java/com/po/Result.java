package com.po;

public class Result {
	private weiboAndtianya statu;
	private String name;
	public weiboAndtianya getStatu() {
		return statu;
	}
	public void setStatu(weiboAndtianya statu) {
		this.statu = statu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Result(weiboAndtianya statu,String name) {
		this.name = name;
		this.statu = statu;
	}
}
