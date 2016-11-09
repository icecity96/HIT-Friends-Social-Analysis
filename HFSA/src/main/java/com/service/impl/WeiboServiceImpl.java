package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dao.AuthorzeDao;
import com.dao.FriendsDao;
import com.dao.UserDao;
import com.dao.WTDao;
import com.dao.WeiboDao;
import com.po.weiboAndtianya;
import com.service.WeiboService;
import com.util.StringUtil;

import weibo4j.Account;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
@Service
public class WeiboServiceImpl implements WeiboService{
	@Autowired
	private AuthorzeDao authorzeDao;
	@Autowired
	private WeiboDao weiboDao;
	@Autowired
	private FriendsDao friendsDao;
	@Autowired
	private WTDao wtDao;
	@Autowired
	private UserDao userDao;
	/**
	 * @author ice_city
	 * @param userId 网站用户id
	 * @return 用户好友列表,授权用户可以全部返回，非授权用户只能返回30%
	 */
	@Override
	public List<User> getFriendsList(int userId) {
		AccessToken token = authorzeDao.getWeiboAccessToken(userId);
		String accessToken = token.getAccessToken();
		Friendships friendships = new Friendships(accessToken);
		Account account = new Account(accessToken);
		UserWapper userWapper = null;
		try {
			userWapper = friendships.getFriendsByID(account.getUid().getString("uid"));
		} catch (WeiboException | JSONException e) {
			e.printStackTrace();
		}
		return userWapper.getUsers();
	}

	/**
	 * @author ice_city
	 * @param userId 网站用户id
	 * @return 用户好友最新微博，受限于第三方，只能获取前30%
	 */
	@SuppressWarnings("serial")
	@Override
	public List<Status> getFriendsStatus(int userId) {
		AccessToken token = authorzeDao.getWeiboAccessToken(userId);
		String accessToken = token.getAccessToken();
		Timeline timeline = new Timeline(accessToken);
		int since_id = authorzeDao.getNewestWeibotime(userId);
		Map<String, String> map = new HashMap<String,String>(){{put("since_id", String.valueOf(since_id));}};
		StatusWapper statusWapper = null;
		try {
			statusWapper = timeline.getFriendsTimeline(map);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return statusWapper.getStatuses();
	}

	/**
	 * @author ice_city
	 * @param userId
	 * 这个函数用于将新获得的好友微博存入数据库
	 */
	@Override
	public void saveFriendsStatus(int userId) {
		List<Status> status = getFriendsStatus(userId);
		for (Status friendStatu : status) {
			if (weiboDao.existUserId(Integer.parseInt(friendStatu.getUser().getId())) != 0 &&
					friendStatu.getIdstr() > weiboDao.getLastWeiboId(Integer.parseInt(friendStatu.getUser().getId()))) {
				weiboDao.saveFriendWeibo(Integer.parseInt(friendStatu.getUser().getId()), friendStatu);
			}
		}
	}
	
	@Override
	//@Scheduled(cron="0 0 */1 * *")	//Hope this will exec per hour
	public void weiboSpider() {
		List<String> urList = wtDao.ReturnWeiboUrl();
		if (urList.isEmpty()) {
			return;
		}
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		for (String url : urList) {
			List<weiboAndtianya> weiboAndtianyas2 = weiboSingnal(driver, url);
			weiboAndtianyas.addAll(weiboAndtianyas2);
		}
		driver.close();
		driver.quit();
		for (weiboAndtianya weiboAndtianya : weiboAndtianyas) {
			try {
				wtDao.insertone(weiboAndtianya);
			} catch (Exception e) {
				continue;
			}	
		}
	}
	
	/**
	 * 获取特定url地址的爬虫
	 * @param driver
	 * @param url
	 * @return
	 */
	public List<weiboAndtianya> weiboSingnal(WebDriver driver,String url) {
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		try {
			driver.get(url);
			WebElement wbFeed = driver.findElement(By.cssSelector("div[class*='WB_feed WB_feed_v3']"));
	        List<WebElement> status = wbFeed.findElements(By.cssSelector("div[class*='WB_detail']"));
	        for (WebElement statu : status) {
				String time = statu.findElement(By.cssSelector("div[class*='WB_from S_txt2']"))
						.findElement(By.cssSelector("a[target*='_blank']")).getAttribute("title");
	        	time = time.replaceAll("[\t| |:|-]", "")+"00";
				String context = statu.findElement(By.cssSelector("div[class*='WB_text']")).getText();
				weiboAndtianyas.add(new weiboAndtianya(url, time, context, "weibo"));
	        }
		} catch (Exception e) {
			return null;
		}
		return weiboAndtianyas;
	}

	@Override
	public void oneurlSpider(String url) {
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		//before this step make sure your firefox has installed
		//in default location.And you have installed geckodriver
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		List<weiboAndtianya> weiboAndtianyas2 = weiboSingnal(driver, url);
		try {
			weiboAndtianyas.addAll(weiboAndtianyas2);
		} catch (Exception e) {

		}		
		driver.close();
		driver.quit();
		for (weiboAndtianya weiboAndtianya : weiboAndtianyas) {
			try {
				wtDao.insertone(weiboAndtianya);
			} catch (Exception e) {
				continue;
			}
		}
		
	}
	
}
