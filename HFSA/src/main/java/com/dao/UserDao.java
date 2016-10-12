package com.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.po.User;

@Repository
public interface UserDao {
	public User findByNicknameOrEmail(@Param("param")Map<String, Object> param);
	public void creatUser(@Param("user")User user);
}
