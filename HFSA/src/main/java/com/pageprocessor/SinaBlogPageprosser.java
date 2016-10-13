package com.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SinaBlogPageprosser implements PageProcessor {
	// 抓取间隔3s，重试次数3次，
	private Site site = Site.me().setDomain("blog.sina.com.cn").setSleepTime(3000).setRetrySleepTime(3);
	// 列表页链接正则
	private final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_\\w+\\.html";
	// 文章页链接正则
	private final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

	@Override
	public Site getSite() {
		return this.site;
	}

	@Override
	public void process(Page page) {
		// 列表页加入等待爬取队列
		page.addTargetRequests(page.getHtml().xpath("//*[@id=\"blognav\"]//div[2]//span[2]").links().all());
		if (page.getUrl().regex(URL_LIST).match()) {
			// 列表页加入
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_LIST).all());
			// 文章页加入
			page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
		} else {
			page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
			page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
			page.putField("date",
					page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
		}
	}

	public static void run(String url) {
		Spider.create(new SinaBlogPageprosser()).addUrl(url).run();
	}
}
