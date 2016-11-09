package com.dao;
import java.util.List;
import com.po.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface FriendsDao {
	/**
	 * 
	 * @select 最新十条动态，不够十条就返回全部 并按照时间排序
	 */
	@Select("select url,time,context,type from weiboandtianya inner join wturl wt on wt.weibourl=url or wt.tianyaurl=url order by time desc limit 100")
	public List<weiboAndtianya> latestMovement();
	
	@Insert("insert into wturl select friendweibo,friendtianya from friends where id=#{id}")
	public void findfriend(@Param("id")long id);
	@Delete("delete from wturl")
	public void clean();
	
	@Select("select friendname from friends where friendweibo=#{mov.url} or friendtianya=#{mov.url}")
	public String FindFriendByUrl(@Param("mov")weiboAndtianya mov);
	/**
	 * 
	 * @delete 好友 按照用户id和好友姓名来进行检索并删除
	 */
	@Delete("delete from friends where friendname=#{friendname} and id=#{id}")
	public void deletefriend(@Param("friendname")String friendname,@Param("id")long id);
	/**
	 * 
	 * @update 改变好友名称 也是按照用户id和好友原名检索修改
	 */
	@Update("update friends set friendname=#{newname} where friendname=#{prename} and id=#{id}")
	public void changeFriendname(@Param("prename")String prename,@Param("newname")String newname,@Param("id")long id);
	/**
	 * 
	 * 同上 改微博url
	 */
	@Update("update friends set friendweibo=#{newweibo} where friendweibo=#{preweibo} and id=#{id}")
	public void changeFriendweibo(@Param("preweibo")String preweibo,@Param("newweibo")String newweibo,@Param("id")long id);
	/**
	 * 
	 * 同上 改变天涯url
	 */
	@Update("update friends set friendtianya=#{newtianya} where friendtianya=#{pretianya} and id=#{id}")
	public void changeFriendtianya(@Param("pretianya")String pretianya,@Param("newtianya")String newtianya,@Param("id")long id);
	/**
	 * 
	 * @insert 把朋友实体类插入数据库
	 */
	@Insert("insert into friends values(#{friend.id},#{friend.friendname},#{friend.friendweibo},#{friend.friendtianya})")
	public void insertFriend(@Param("friend")Friends friend);
	/**
	 * 
	 * 传入朋友的天涯和微博url 并以此取得所有动态
	 */
	@Select("select * from weiboAndtianya where url=#{wurl} or url={turl}")
	public List<weiboAndtianya> returnFriendsMovement(@Param("wurl")String weibourl,@Param("turl")String tianyaurl);
	/**
	 * 
	 * 查询朋友是否存在，仅针对某一用户而言。因此参数为id和朋友name
	 */
	@Select("select count(*) from friends where friendname=#{name} and id=#{id}")
	public int ifFriendexist(@Param("name")String friendname,@Param("id")long id);
	/**
	 * 
	 * @return 所有friendname
	 */
	@Select("select friendname from friends where id=#{id}")
	public List<String> allFriends(@Param("id")long id);
}
