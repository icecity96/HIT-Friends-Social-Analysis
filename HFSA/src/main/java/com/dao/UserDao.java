package com.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.po.User;
public interface UserDao {
	@Select ("select * from user where nickname=#{param.nickname} or email=#{param.email}")
	public User findByNicknameOrEmail(@Param("param")Map<String, Object> param);
	@Insert ("insert into user values(#{user.id},#{user.nickname},#{user.email},#{user.password},#{user.headImg},#{user.createTime},#{user.updateTime})")
	public void creatUser(@Param("user")User user);
	@Select("select max(id) from user")
	public int getMaxid();
}
