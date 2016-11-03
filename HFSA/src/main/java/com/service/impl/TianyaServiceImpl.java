package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.dao.WTDao;
import com.po.weiboAndtianya;
import com.service.TianyaService;
import com.util.StringUtil;

public class TianyaServiceImpl implements TianyaService{
	@Autowired
	private WTDao wtDao;
	@Override
	@Scheduled(cron="0 0 */1 * *")
	public void TianyaSpider() {
		List<String> tianYaURL = wtDao.returnTianyaUrl();
		//空直接退出
		if (tianYaURL.isEmpty()) {
			return;
		}
		List<weiboAndtianya> weiboAndtianyas = new ArrayList<weiboAndtianya>();
		//before this step make sure your firefox has installed
		//in default location.And you have installed geckodriver
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		for (String url : tianYaURL) {
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
					String timeString = statu.findElement(By.cssSelector("a[target*='_blank']")).getText();
					timeString = StringUtil.parseTianYatime(timeString);
					String context = statu.findElement(By.className("article")).getText();
					weiboAndtianyas.add(new weiboAndtianya(url,timeString,context,"tianya"));
				}
			} catch (Exception e) {
				continue;
			}
		}
		wtDao.insertTianya(weiboAndtianyas);
		driver.close();
	}
}
