package com.dao;

import java.util.List;
import com.po.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

public interface WTDao {
	/**
	 * 
	 * @return 所有不重复的微博url
	 */
	@Select("select distinct Wurl from weiboAndtianya where type='weibo'")
	List<String> ReturnWeiboUrl();
	/**
	 * 
	 * @return 所有不重复的天涯url
	 */
	@Select("select distinct Turl from weiboAndtianya where type='tianya'")
	List<String> ReturnTianyaUrl();
	/**
	 * 
	 * @insert 微博（重复的无法插入）
	 */
	@InsertProvider(type=wtProvider.class, method="insert")
	public void insertWeiboandTianya(List<weiboAndtianya> list);
	
}
