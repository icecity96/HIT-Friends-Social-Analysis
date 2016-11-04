package com.dao;
import java.util.List;
import com.po.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

public interface FriendsDao {
	//最新十条动态
	@Select("select * from weiboAndtianya where belongto=#{id} order by time limit 10")
	List<weiboAndtianya> latestMovement(@Param("id")int id);
	//删除指定好友
	@Delete("delete from friends where friendname=#{friendname} and id=#{id}")
	void deletefriend(@Param("friendname")String friendname,@Param("id")long id);
	//改变好友名
	@Update("update friends set friendname=#{newname} where friendname=#{prename} and id=#{id}")
	void changeFriendname(@Param("prename")String prename,@Param("newname")String newname,@Param("id")long id);
	//改变好友微博url
	@Update("update friends set friendweibo=#{newweibo} where friendweibo=#{preweibo} and id=#{id}")
	void changeFriendweibo(@Param("preweibo")String preweibo,@Param("newweibo")String newweibo,@Param("id")long id);
	//改变好友天涯url
	@Update("update friends set friendtianya=#{newtianya} where friendtianya=#{pretianya} and id=#{id}")
	void changeFriendtianya(@Param("pretianya")String pretianya,@Param("newtianya")String newtianya,@Param("id")long id);
	//新增好友
	@Insert("insert into friends values(#{friend.id},#{friend.friendname},#{friend.friendweibo},#{friend.friendtianya}")
	void insertFriend(@Param("friend")Friends friend);
	//返回好友的所有天涯动态以及微博动态
	@Select("select * from weiboAndtianya where url=#{wurl} or url={turl}")
	List<weiboAndtianya> returnFriendsMovement(@Param("wurl")String weibourl,@Param("turl")String tianyaurl);
	//查询好友是否存在，有则为正数，否则为0
	@Select("select count(*) from friends where friendname=#{name}")
	int ifFriendexist(@Param("name")String friendname);
	//返回某用户的所有好友名
	@Select("select friendname from friends where id=#{id}")
	List<String> allFriends(@Param("id")int id);
}
