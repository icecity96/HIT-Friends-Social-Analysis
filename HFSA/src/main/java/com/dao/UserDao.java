
package com.dao;

import java.util.Map;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.po.User;
public interface UserDao {
	
	@Select("select * from user where nickname=#{param.nickname} or email=#{param.email}")
	public User findByNicknameOrEmail(@Param("param")Map<String, Object> param);
	@Insert("insert into user values(#{user.id},#{user.password},#{user.nickname},#{user.email},#{user.headImg},#{user.createTime},#{user.updateTime})")
	public void creatUser(@Param("user")User user);
	/**
	 * 获取数据库中所有注册用户的id
	 * @return
	 */
	@Select("select id from user")
	public List<Integer> getAllUserId();
	@Select("select max(id) from user")
	public int getMaxid();
}
