package com.dao;

import java.util.List;
import com.po.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WTDao {
	/**
	 * 
	 * @return 所有不重复的微博url
	 */
	@Select("select distinct friendweibo from friends ")
	List<String> ReturnWeiboUrl();
	/**
	 * 
	 * @return 所有不重复的天涯url
	 */
	@Select("select distinct friendweibo from friends")
	List<String> ReturnTianyaUrl();
	/**
	 * 
	 * @insert 单条微博（重复的无法插入，捕捉DuplicateKeyException异常即可。） 
	 */
	@Insert("insert into weiboandtianya values(#{wt.belongto},#{wt.url},#{wt.time},#{wt.context},#{wt.type})")
	public void insertone(@Param("wt")weiboAndtianya wt);
	
}