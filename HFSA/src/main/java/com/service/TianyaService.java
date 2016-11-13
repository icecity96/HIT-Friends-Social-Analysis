package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TianyaService {
	public void TianyaSpider() throws FileNotFoundException, ClassNotFoundException, IOException;
	public void oneurlSpider(String url) throws FileNotFoundException, ClassNotFoundException, IOException;
}
