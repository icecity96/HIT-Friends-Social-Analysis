package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface WeiboService {
	public void weiboSpider() throws FileNotFoundException, ClassNotFoundException, IOException;
	public void oneurlSpider(String url) throws FileNotFoundException, ClassNotFoundException, IOException;
}
