package com.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.po.User;

public interface UserDao {
	
	@Select ("select * from userInfo where param.nickname=#{nickname} or param.email=#{email}")
	public User findByNicknameOrEmail(@Param("param")Map<String, Object> param);
	@Insert ("insert into userInfo values(#{user.id},#{user.nickname},#{user.email},#{user.password},#{user.headImg},#{user.createTime},#{user.updateTime})")
	public void creatUser(@Param("user")User user);
}
