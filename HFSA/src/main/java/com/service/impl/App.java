package com.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import javax.management.JMException;

import org.apache.log4j.BasicConfigurator;

import com.pageprocessor.SinaBlogPageprosser;
import com.pageprocessor.SinaWeiboPageprocessor;
import com.util.FontImgUtil;
import com.util.StringUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.selector.Html;

public class App {
	public static void main(String[] args) {
		SinaBlogPageprosser.run("http://blog.sina.com.cn/zsnloveminty");
		SinaWeiboPageprocessor.run("http://weibo.com/p/1005055729812399/home?from=page_100505&mod=TAB&is_all=1#place");
	}
}
