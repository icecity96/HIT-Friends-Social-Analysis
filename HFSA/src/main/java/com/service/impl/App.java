package com.service.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.po.User;
import com.service.UserServer;

public class App {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
		UserServer server = (UserServer) context.getBean("UserServerImpl");
		User user = new User();
		user.setPassword("he");
		user.setEmail("hh");
		server.register(user);
	}
}
