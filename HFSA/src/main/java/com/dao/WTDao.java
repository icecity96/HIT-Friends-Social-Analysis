package com.dao;

import java.util.List;
import com.po.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
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
	@InsertProvider(type=InsertProvider.class, method="insertWeibo")
	public void insertWeibo(List<weiboAndtianya> list);
	/**
	 * 
	 * @insert 天涯（重复的无法插入）
	 */
	@InsertProvider(type=InsertProvider.class, method="insertTianya")
	public void insertTianya(List<weiboAndtianya> list);
}
