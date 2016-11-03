package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.po.User;
public interface UserDao {
	@Select ("select * from user where nickname=#{param.nickname} or email=#{param.email}")
	public User findByNicknameOrEmail(@Param("param")Map<String, Object> param);
	@Insert ("insert into user values(#{user.id},#{user.password},#{user.nickname},#{user.email},#{user.headImg},#{user.createTime},#{user.updateTime})")
	public void creatUser(@Param("user")User user);
	@Select("select max(id) from user")
	public int getMaxid();
	/**
	 * 获取数据库中所有注册用户的id
	 * @return
	 */
	public List<Integer> getAllUserId();
}
