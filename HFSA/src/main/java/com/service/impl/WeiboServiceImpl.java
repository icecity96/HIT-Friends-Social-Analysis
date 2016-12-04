package com.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.WTDao;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.po.weiboAndtianya;
import com.service.WeiboService;
import com.util.StringUtil;
@Service
public class WeiboServiceImpl implements WeiboService{
	@Autowired
	private WTDao wtDao;
	@Override
	//@Scheduled(cron="0 0 */1 * *")	//Hope this will exec per hour
	public void weiboSpider() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<String> urList = wtDao.ReturnWeiboUrl();
		if (urList.isEmpty()) {
			return;
		}
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		FirefoxDriver driver = new FirefoxDriver();
		//PhantomJSDriver driver = new PhantomJSDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		for (String url : urList) {
			if(url.isEmpty()) continue;
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
		return;
	}
	
	/**
	 * 获取特定url地址的爬虫
	 * @param driver
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public List<weiboAndtianya> weiboSingnal(WebDriver driver,String url) throws FileNotFoundException, ClassNotFoundException, IOException {
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
				int topic = StringUtil.getTopic(context);
				weiboAndtianyas.add(new weiboAndtianya(url, time, context, "weibo",topic));
	        }
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return weiboAndtianyas;
	}

	@Override
	public void oneurlSpider(String url) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		//before this step make sure your firefox has installed
		//in default location.And you have installed geckodriver
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		List<weiboAndtianya> weiboAndtianyas2 = weiboSingnal(driver, url);
		driver.close();
		driver.quit();
		try {
			weiboAndtianyas.addAll(weiboAndtianyas2);
		} catch (Exception e) {
			return ;
		}		
		for (weiboAndtianya weiboAndtianya : weiboAndtianyas) {
			try {
				wtDao.insertone(weiboAndtianya);
			} catch (Exception e) {
				continue;
			}
		}
		
	}
	
}
