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
	
	@Select("select url,time,context,type,topic from weiboandtianya inner join wturl wt on wt.weibourl=url or wt.tianyaurl=url order by time desc limit 100")
	public List<weiboAndtianya> latestMovement();
	//返回某人所有朋友动态所用//
	@Insert("insert into wturl select friendname,friendweibo,friendtianya from friends where id=#{id}")
	public void findfriend(@Param("id")long id);
	//返回某个特定朋友//
	@Insert("insert into wturl select friendname,friendweibo,friendtianya from friends where id=#{id} and friendname=#{friendname}")
	public void findOnefriend(@Param("id")long id,@Param("friendname")String friendname);
	//----------//
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
	 * 5个参数分别指：之前的名字prename  新名字newname  用户id 新微博newweibo  新天涯newtianya
	 */
	
	@Update("update friends set friendname=#{newname},friendweibo=#{newweibo},friendtianya=#{newtianya} where friendname=#{prename} and id=#{id}")
	public void changeFriend(@Param("prename")String prename,@Param("newname")String newname,@Param("id")long id,@Param("newweibo")String newweibo,@Param("newtianya")String newtianya);
	
	/**
	 * 
	 * @insert 把朋友实体类插入数据库
	 */
	@Insert("insert into friends values(#{friend.id},#{friend.friendname},#{friend.friendweibo},#{friend.friendtianya})")
	public void insertFriend(@Param("friend")Friends friend);
	
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
	@Select("select * from friends where id=#{id}")
	public List<Friends> allFriends(@Param("id")long id);
}
