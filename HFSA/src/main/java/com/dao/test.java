package com.dao;
import com.po.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class test {
	@Autowired
	public  WTDao wtdao;
	@Autowired
	public  FriendsDao f;
	public  void deletefriend()
	{
		String friendname="jack";
		int id=1;
		f.deletefriend(friendname, id);
	}
}