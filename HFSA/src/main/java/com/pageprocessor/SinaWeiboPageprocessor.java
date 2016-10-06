package com.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SinaWeiboPageprocessor implements PageProcessor{
	//抓取间隔3s，重试次数3次，
	private Site site = Site.me().setDomain("weibo.com").setSleepTime(3000).setRetrySleepTime(3);
	@Override
	public Site getSite() {
		return this.site;
	}

	@Override
	public void process(Page page) {
		//*[@id="Pl_Official_MyProfileFeed__24"]/div/div[2]/div[1]/div[3]/div[3]
		page.putField("name", page.getHtml().xpath("//*[@id=\"Pl_Official_Headerv6__1\"]/div/div/div[2]/div[2]/h1"));
		page.putField("data", page.getHtml().xpath("//*[@id=\"Pl_Official_MyProfileFeed__24\"]//div//div[2]//div[1]//div[3]//div[2]/a[1]"));
		page.putField("context", page.getHtml().xpath("//*[@id=\"Pl_Official_MyProfileFeed__24\"]//div//div[2]//div[1]//div[3]/div[3]"));
		page.putField("other", page.getHtml().xpath("//*[@id=\"Pl_Official_MyProfileFeed__24\"]//div//div[2]//div[1]//div[3]/div[4]"));
	}
	
	public static void run(String url) {
		Spider.create(new SinaWeiboPageprocessor()).addUrl(url).run();
	}
}
