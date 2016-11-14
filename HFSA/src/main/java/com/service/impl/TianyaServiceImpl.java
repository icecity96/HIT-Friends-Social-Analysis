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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dao.WTDao;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.po.weiboAndtianya;
import com.service.TianyaService;
import com.util.StringUtil;

@Service
public class TianyaServiceImpl implements TianyaService{
	@Autowired
	private WTDao wtDao;
	@Override
	//@Scheduled(cron="0 0 */1 * *")
	public void TianyaSpider() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<String> tianYaURL = wtDao.ReturnTianyaUrl();
		//空直接退出
		if (tianYaURL.isEmpty()) {
			return;
		}
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		//before this step make sure your firefox has installed
		//in default location.And you have installed geckodriver
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		for (String url : tianYaURL) {
			List<weiboAndtianya> weiboAndtianyas2 = tianyaSingnal(driver, url);
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public List<weiboAndtianya> tianyaSingnal(WebDriver driver,String url) throws FileNotFoundException, ClassNotFoundException, IOException {
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		try {
			driver.get(url);
			//close login window
			driver.findElement(By.cssSelector("a[class*='loginWin-close-btn']")).click();
			//show time_line
			driver.findElement(By.cssSelector(
					"#home_wrapper > div.main-area > div.home-module.module-usersuiji > div.mod-hd > a"))
					.click();
			WebElement timeline = driver.findElement(By.cssSelector(
					"#home_wrapper > div.main-area > div.moudle-timeline.animated.slideInLeft"));
			List<WebElement> status = timeline.findElements(By.className("feed-content-item"));
			for (WebElement statu : status) {
				try {
				String timeString = statu.findElement(By.cssSelector("a[target*='_blank']")).getText();
				timeString = StringUtil.parseTianYatime(timeString);
				String context = statu.findElement(By.className("article")).getText();
				int topic = -1;
				topic = StringUtil.getTopic(context);
				weiboAndtianyas.add(new weiboAndtianya(url,timeString,context,"tianya",topic));
				} catch (Exception e) {
					break;
				}
			}
		} catch (ElementNotFoundException e) {
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
		List<weiboAndtianya> weiboAndtianyas2 = tianyaSingnal(driver, url);
		driver.close();
		driver.quit();
		if (weiboAndtianyas2 == null) {
			return;
		}
		weiboAndtianyas.addAll(weiboAndtianyas2);
		for (weiboAndtianya weiboAndtianya : weiboAndtianyas) {
			try {
				wtDao.insertone(weiboAndtianya);
			} catch (Exception e) {
				continue;
			}
		}
	}
}
