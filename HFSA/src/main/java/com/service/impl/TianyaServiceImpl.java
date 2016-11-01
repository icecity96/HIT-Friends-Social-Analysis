package com.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.dao.TianyaDao;
import com.service.TianyaService;

public class TianyaServiceImpl implements TianyaService{
	@Autowired
	private TianyaDao tianyaDao;
	@Override
	@Scheduled(cron="0 0 */1 * *")
	public void TianyaSpider() {
		List<String> tianYaURL = tianyaDao.getAllURLs();
		//空直接退出
		if (tianYaURL.isEmpty()) {
			return;
		}
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
					String title = statu.findElement(By.className("title")).getText();
					String timeString = statu.findElement(By.cssSelector("a[target*='_blank']")).getText();
					String context = statu.findElement(By.className("article")).getText();
					//TODO 构建类，处理时间 icecity
				}
			} catch (Exception e) {
				continue;
			}
		}
		driver.close();
	}
}
