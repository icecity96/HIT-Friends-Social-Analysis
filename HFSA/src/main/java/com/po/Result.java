package com.po;

public class Result implements Comparable {
	private weiboAndtianya statu;
	private String name;
	public Result(weiboAndtianya statu,String name) {
		this.setName(name);
		this.setStatu(statu);
	}
	public Result() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public weiboAndtianya getStatu() {
		return statu;
	}
	public void setStatu(weiboAndtianya statu) {
		this.statu = statu;
	}
	@Override
	public int compareTo(Object o) {
		Result tResult = (Result)o;
		return tResult.getStatu().getTime().compareTo(this.getStatu().getTime());
	}
}
